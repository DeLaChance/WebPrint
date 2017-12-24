package nl.webprint.util;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBusOptions;

import java.util.function.Consumer;

/**
 * @author Lucien Valstar (lucien.valstar@asml.com)
 */
public class Runner {

	public final static Integer EVENT_BUS_PORT = 1234;
	public final static String EVENT_BUS_HOST = "localhost";

	private Runner() {
	}

	public static void run(final Class klass) {
		final VertxOptions vertxOptions = new VertxOptions()
			.setClustered(true)
			.setEventBusOptions(new EventBusOptions())
			.setClusterPublicHost(EVENT_BUS_HOST)
			.setClusterPublicPort(EVENT_BUS_PORT);

		final DeploymentOptions deploymentOptions = new DeploymentOptions();
		final String verticleID = klass.getName();

		final Consumer<Vertx> runner = vertx -> {
			try {
				if (deploymentOptions != null) {
					vertx.deployVerticle(verticleID, deploymentOptions);
				} else {
					vertx.deployVerticle(verticleID);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		};

		if (vertxOptions.isClustered()) {
			Vertx.clusteredVertx(vertxOptions, res -> {
				if (res.succeeded()) {
					final Vertx vertx = res.result();
					runner.accept(vertx);
				} else {
					res.cause().printStackTrace();
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(vertxOptions);
			runner.accept(vertx);
		}
	}

}
