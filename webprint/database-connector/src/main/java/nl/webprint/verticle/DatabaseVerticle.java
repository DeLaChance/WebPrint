package nl.webprint.verticle;

import static nl.webprint.messaging.Config.HTTP_SERVER_CHANNEL;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import nl.webprint.messaging.PrintingJobDeletionRequest;
import nl.webprint.messaging.PrintingJobRequest;
import nl.webprint.messaging.PrintingJobResponse;
import io.vertx.ext.sql.ResultSet;
import nl.webprint.util.MessageSender;
import nl.webprint.util.Runner;

/**
 * Verticle that communicates with the database.
 *
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class DatabaseVerticle extends AbstractVerticle {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private PrintingJobRepository printingJobRepository;
	
	public DatabaseVerticle() {
		super();
	}

	public static void main(final String[] args) {
		Runner.run(DatabaseVerticle.class);
	}

	@Override
	public void start() {
		this.printingJobRepository = new PrintingJobRepository(this.vertx);
		
		final EventBus eventBus = this.vertx.eventBus();

		final MessageConsumer<String> messageConsumer = eventBus.consumer(HTTP_SERVER_CHANNEL);
		messageConsumer.handler((Message<String> message) -> {
			try {
				final MultiMap headers = message.headers();
				final String type = headers.get("type");
				final String messageBody = message.body();

				final Future<Void> future = Future.future();
				
				switch(type) {
					case "PrintingJobRequest":
						final PrintingJobRequest printingJobRequest = objectMapper.readValue(messageBody, PrintingJobRequest.class);
						this.handle(message, printingJobRequest, future.completer());
						break;
					case "PrintingJobDeletionRequest":
						final PrintingJobDeletionRequest printingJobDeletionRequest = objectMapper.readValue(messageBody, PrintingJobDeletionRequest.class);
						this.handle(message, printingJobDeletionRequest, future.completer());
						break;
					default:
				}
			
			} catch(final IOException ioe) {
				ioe.printStackTrace();
			}
		});
		
		System.out.println("Started DatabaseVerticle");
		

	}
	
	private void handle(Message<String> message, PrintingJobDeletionRequest printingJobDeletionRequest,
			Handler<AsyncResult<Void>> aHandler) {
		
		Future<Void> finalFuture = Future.future();
		finalFuture.setHandler(aHandler);
		
		Future<Boolean> deleteInDBTask = Future.future();
		this.printingJobRepository.deletePrintingJob(printingJobDeletionRequest, deleteInDBTask);
		
		deleteInDBTask.compose(succeeded -> {
			System.out.println("Sending printing job deletion response");
			
			MessageSender.sendPrintingJobDeletionResponse(message, succeeded);
			finalFuture.complete();
		}, finalFuture);
	}

	private void handle(final Message<String> message, final PrintingJobRequest printingJobRequest, Handler<AsyncResult<Void>> aHandler) {
		Future<Void> finalFuture = Future.future();
		finalFuture.setHandler(aHandler);		
		
		// Fetch DB records
		Future<PrintingJobResponse> fetchSQLtask = Future.future();
		this.printingJobRepository.fetchPrintingJobs(printingJobRequest, fetchSQLtask);
		
		// Send a reply
		fetchSQLtask.compose(printingJobResponse -> {
			System.out.println("Sending printing job response");
			
			MessageSender.sendPrintingJobResponse(message, printingJobResponse);
			finalFuture.complete();
		}, finalFuture);
	}

	@Override
	public void stop() {

	}
}
