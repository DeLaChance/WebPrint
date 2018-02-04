package nl.webprint.configuration;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Completable;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

/**
 * Starts up the verticles/modules to deploy.
 */
public class Standalone extends AbstractVerticle
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Standalone.class);
	
	@Override
	public void init(final Vertx vertx, final Context context) {
		LOGGER.info("Initialized the application.");
	}
	
	@Override
	public void start(final Future<Void> startFuture) {
		Completable.fromAction(() -> LOGGER.info("Starting up the application..."))
			.subscribe(startFuture::complete, startFuture::fail);
	}
	
	
}
