package nl.webprint.printing;

import java.util.List;

import io.reactivex.Maybe;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import nl.webprint.adapter.http.FileUpload;
import nl.webprint.configuration.AddressConfiguration;

/**
 * A repository holding the printing jobs
 * 
 * @author lucien
 *
 */
@ProxyGen
@VertxGen
public interface PrintingJobRepository {

	String EVENT_BUS_ADDRESS = AddressConfiguration.PRINTINGJOB_REPOSITORY.getAddress();
	
	static PrintingJobRepository create(Vertx vertx) {
		return new DirectoryBasedPrintingJobRepository(vertx);
	}
	
	static PrintingJobRepository createProxy(Vertx vertx) {
		return new PrintingJobRepositoryVertxEBProxy(vertx, EVENT_BUS_ADDRESS);
	}	
	
	void findAll(Handler<AsyncResult<List<PrintingJob>>> resultHandler);
	
	void findById(PrintingJobIdentifier identifier, Handler<AsyncResult<PrintingJob>> resultHandler);
	
	void findByIdOrError(PrintingJobIdentifier identifier, Handler<AsyncResult<PrintingJob>> resultHandler);
	
	void add(FileUpload uploadedFile, Handler<AsyncResult<PrintingJob>> resultHandler);
	
	void delete(PrintingJobIdentifier identifier, Handler<AsyncResult<Void>> resultHandler);
	
	void update(PrintingJob printingJob, Handler<AsyncResult<Void>> resultHandler);
	
	
}
