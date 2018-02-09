package nl.webprint.printing;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Completable;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.Future;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import nl.webprint.adapter.http.HttpServerVerticle;

/**
 * A printing job repository that persists its data on the file system.
 * Each printing job is put into a subdirectory with one file containing
 * the payload and another file containing the metadata.
 * 
 * @author lucien
 *
 */
public class DirectoryBasedPrintingJobRepository implements PrintingJobRepository {

	public static final String PRINTING_JOB_DIR = "/var/uploads/";
	private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryBasedPrintingJobRepository.class);
	
	private final Vertx vertx;
	
	public DirectoryBasedPrintingJobRepository(final io.vertx.core.Vertx vertx) {
		this.vertx = new Vertx(vertx);
		
		this.vertx.fileSystem().rxExists(PRINTING_JOB_DIR)
			.subscribe(	existsBoolean -> {
				if( !existsBoolean ) {
					this.vertx.fileSystem().rxMkdirs(PRINTING_JOB_DIR)
						.subscribe(
							() ->  LOGGER.info("Directory " + PRINTING_JOB_DIR + " created."),
							throwable -> LOGGER.error("Directory " + PRINTING_JOB_DIR + " was not created: ", throwable)
						);
				}
			});
	}
	
	@Override
	public void findAll(Handler<AsyncResult<List<PrintingJob>>> resultHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findById(PrintingJobIdentifier identifier, Handler<AsyncResult<List<PrintingJob>>> resultHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(UploadedFile uploadedFile, Handler<AsyncResult<Void>> resultHandler) {
		
		final PrintingJob printingJob = PrintingJob.from(uploadedFile, PRINTING_JOB_DIR);		
		Buffer bufferData = Buffer.buffer(printingJob.toJson().encodePrettily());
		
		vertx.fileSystem()
			.rxMkdir(printingJob.getFilePath())
			.andThen(vertx.fileSystem().rxCopy(uploadedFile.getUploadedFilePath(),
				printingJob.getFilePath()))
			.andThen(vertx.fileSystem().rxCreateFile(printingJob.getFilePath() + "/metadata.json"))
			.andThen(vertx.fileSystem().rxOpen(printingJob.getFilePath() + "/metadata.json", 
				new OpenOptions().setRead(true).setWrite(true))
			)
			.flatMapCompletable(asyncFile -> 
				asyncFile.write(bufferData).rxClose()
			)
			.subscribe(
				() -> {
					LOGGER.info("Created printing job for file=" + printingJob.getFileName());
					resultHandler.handle(Future.succeededFuture());
				},
				throwable -> {
					LOGGER.error("Could not create a printing job for file=" + printingJob.getFileName());
					resultHandler.handle(Future.failedFuture(throwable));
				}
			);
		
	}

	@Override
	public void delete(PrintingJobIdentifier identifier, Handler<AsyncResult<Void>> resultHandler) {
		
		vertx.fileSystem()
			.rxDeleteRecursive(PRINTING_JOB_DIR + "/" + identifier.getIdentifier(), true)
			.subscribe(
				() -> {
					LOGGER.info("Deleted printing job with id=" + identifier.getIdentifier().toString());
					resultHandler.handle(Future.succeededFuture());
				},
				throwable -> {
					LOGGER.error("Could not delete the printing job for id=" + identifier.getIdentifier().toString());
					resultHandler.handle(Future.failedFuture(throwable));
				}
			);
		
	}

}
