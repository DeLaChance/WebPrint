package nl.webprint.verticle.database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import nl.webprint.util.Runner;

import static nl.webprint.messaging.Config.HTTP_SERVER_CHANNEL;

/**
 * Verticle that communicates with the databse.
 *
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class DatabaseVerticle extends AbstractVerticle {

	public DatabaseVerticle() {
		super();
	}

	public static void main(final String[] args) {
		Runner.run(DatabaseVerticle.class);
	}

	@Override
	public void start() {
		final EventBus eventBus = this.vertx.eventBus();

		final MessageConsumer<String> messageConsumer = eventBus.consumer(HTTP_SERVER_CHANNEL);
		messageConsumer.handler(message -> {
			System.out.println("I got a message: " + message.body());
		});

		eventBus.consumer(HTTP_SERVER_CHANNEL, message -> {
			message.reply("Pong!");
		});
	}

	@Override
	public void stop() {

	}
}
