package nl.webprint.verticle.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import nl.webprint.messaging.Config;
import nl.webprint.messaging.PrintingJobRequest;

import java.io.IOException;

/**
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class MessageSender {

	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private MessageSender() {
	}

	public static void sendPrintingJobRequest(final Vertx vertx, final String identifier) {
		try {
			final PrintingJobRequest request = PrintingJobRequest.builder()
				.identifier(identifier)
				.build();
			vertx.eventBus().send(Config.HTTP_SERVER_CHANNEL, OBJECT_MAPPER.writeValueAsString(request));
		} catch(final IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void sendPrintingJobRequest(final Vertx vertx) {
		MessageSender.sendPrintingJobRequest(vertx, null);
	}
}
