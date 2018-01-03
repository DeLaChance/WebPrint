package nl.webprint.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import nl.webprint.messaging.Config;
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
		try {
			final PrintingJobRequest request = PrintingJobRequest.builder()
				.identifier(identifier)
				.build();
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", "PrintingJobRequest");
			
			vertx.eventBus().send(Config.HTTP_SERVER_CHANNEL, OBJECT_MAPPER.writeValueAsString(request), options, replyHandler);
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void sendPrintingJobResponse(final Vertx vertx, final PrintingJobResponse response) {
		try {
			final String payload = OBJECT_MAPPER.writeValueAsString(response);
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", "PrintingJobResponse");
			
			vertx.eventBus().send(Config.HTTP_SERVER_CHANNEL, payload, options);
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
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
		try {
			final String payload = OBJECT_MAPPER.writeValueAsString(response);		
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", "PrintingJobResponse");
			
			message.reply(payload, options);
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
