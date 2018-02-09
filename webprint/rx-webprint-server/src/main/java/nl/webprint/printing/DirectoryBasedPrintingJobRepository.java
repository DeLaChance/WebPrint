package nl.webprint.printing;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.core.Vertx;

/**
 * A printing job repository that persists its data on the file system.
 * Each printing job is put into a subdirectory with one file containing
 * the payload and another file containing the metadata.
 * 
 * @author lucien
 *
 */
public class DirectoryBasedPrintingJobRepository implements PrintingJobRepository {

	private final Vertx vertx;
	
	public DirectoryBasedPrintingJobRepository(final io.vertx.core.Vertx vertx) {
		this.vertx = new Vertx(vertx);
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
	public void add(PrintingJob printingJob, Handler<AsyncResult<Void>> resultHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PrintingJobIdentifier identifier, Handler<AsyncResult<Void>> resultHandler) {
		// TODO Auto-generated method stub
		
	}

}
