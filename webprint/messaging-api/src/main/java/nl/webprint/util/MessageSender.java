package nl.webprint.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import nl.webprint.messaging.Config;
import nl.webprint.messaging.PrintingJobDeletionRequest;
import nl.webprint.messaging.PrintingJobDeletionResponse;
import nl.webprint.messaging.PrintingJobRequest;
import nl.webprint.messaging.PrintingJobResponse;

import java.io.IOException;

/**
 * This class can send messages in an agreed format over the event-bus.
 * 
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class MessageSender {

	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private MessageSender() {
	}

	public static void sendPrintingJobRequest(final Vertx vertx, final Handler<AsyncResult<Message<String>>> replyHandler) {
		MessageSender.sendPrintingJobRequest(vertx, null, replyHandler);
	}
	
	public static void sendPrintingJobRequest(final Vertx vertx, final String identifier, final Handler<AsyncResult<Message<String>>> replyHandler) {
		final PrintingJobRequest request = PrintingJobRequest.builder()
			.identifier(identifier)
			.build();

		MessageSender.send(vertx, request, PrintingJobRequest.class, replyHandler);
	}

	/**
	 * Replies to a message that was sent to request a list of printing jobs.
	 * 
	 * @param message
	 * 				The request for a list of printing jobs
	 * @param response
	 * 				The response, containing that list
	 */
	public static void sendPrintingJobResponse(Message<String> message, PrintingJobResponse response) {
		
		MessageSender.sendReply(message, response, PrintingJobResponse.class);
	}
	
	public static void sendPrintingJobDeletion(Vertx vertx, String identifier,
			Handler<AsyncResult<Message<String>>> replyHandler) {
		
		final PrintingJobDeletionRequest request = PrintingJobDeletionRequest.builder()
			.identifier(identifier)
			.build();

		MessageSender.send(vertx, request, PrintingJobDeletionRequest.class, replyHandler);
		
	}
	
	public static void sendPrintingJobDeletionResponse(final Message<String> message,final Boolean succeeded) {

		final PrintingJobDeletionResponse response = PrintingJobDeletionResponse.builder()
			.succeeded(succeeded)
			.build();
		
		MessageSender.sendReply(message, response, PrintingJobDeletionResponse.class);
	}	
	
	private static <T> void send(final Vertx vertx,final T object, final Class<T> klass, final Handler<AsyncResult<Message<String>>> replyHandler) {
		try {
			final String payload = OBJECT_MAPPER.writeValueAsString(object);
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", klass.getSimpleName());
			
			vertx.eventBus().send(Config.HTTP_SERVER_CHANNEL,  payload, options, replyHandler);			
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static <T> void sendReply(final Message<String> message, final T response, final Class<T> klass) {
		
		try {
			final String payload = OBJECT_MAPPER.writeValueAsString(response);	
			
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", klass.getSimpleName());
			
			message.reply(payload, options);
			
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}
