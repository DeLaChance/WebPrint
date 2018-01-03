package nl.webprint.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import nl.webprint.messaging.Config;
import nl.webprint.messaging.PrintingJobRequest;
import nl.webprint.messaging.PrintingJobResponse;

import java.io.IOException;

/**
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class MessageSender {

	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private MessageSender() {
	}

	public static void sendPrintingJobRequest(final Vertx vertx) {
		MessageSender.sendPrintingJobRequest(vertx, null);
	}
	
	public static void sendPrintingJobRequest(final Vertx vertx, final String identifier) {
		try {
			final PrintingJobRequest request = PrintingJobRequest.builder()
				.identifier(identifier)
				.build();
			final DeliveryOptions options = new DeliveryOptions();
			options.addHeader("type", "PrintingJobRequest");
			
			vertx.eventBus().send(Config.HTTP_SERVER_CHANNEL, OBJECT_MAPPER.writeValueAsString(request), options);
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
}
