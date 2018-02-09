package nl.webprint.printing;

import java.util.List;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
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
	
	static PrintingJob create(Vertx vertx) {
		throw new UnsupportedOperationException();
	}
	
	static PrintingJob createProxy(Vertx vertx) {
		throw new UnsupportedOperationException();
	}	
	
	void findAll(Handler<AsyncResult<List<PrintingJob>>> resultHandler);
	
	void findById(PrintingJobIdentifier identifier, Handler<AsyncResult<List<PrintingJob>>> resultHandler);
	
	void add(PrintingJob printingJob, Handler<AsyncResult<Void>> resultHandler);
	
	void delete(PrintingJobIdentifier identifier, Handler<AsyncResult<Void>> resultHandler);
}
