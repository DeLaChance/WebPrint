package nl.webprint;

import io.vertx.core.Future;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;
import nl.webprint.adapter.http.HttpServerVerticle;
import nl.webprint.configuration.ConfigurationRepository;
import nl.webprint.printing.PrintingJobRepository;
import nl.webprint.printing.PrintingVerticle;

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
	public void start(final Future<Void> startFuture) throws Exception {
		
		new ServiceBinder(vertx.getDelegate())
			.setAddress(ConfigurationRepository.EVENT_BUS_ADDRESS)
			.register(ConfigurationRepository.class, ConfigurationRepository.create(vertx.getDelegate()));
		
		new ServiceBinder(vertx.getDelegate())
			.setAddress(PrintingJobRepository.EVENT_BUS_ADDRESS)
			.register(PrintingJobRepository.class, PrintingJobRepository.create(vertx.getDelegate()));
		
		Completable.fromAction(() -> LOGGER.info("Starting up the application..."))
			.andThen( this.vertx.rxDeployVerticle(HttpServerVerticle.class.getName()) ).toCompletable()
			.andThen( this.vertx.rxDeployVerticle(PrintingVerticle.class.getName()) ).toCompletable()
			.subscribe(startFuture::complete, startFuture::fail);
	}
	
	
}
