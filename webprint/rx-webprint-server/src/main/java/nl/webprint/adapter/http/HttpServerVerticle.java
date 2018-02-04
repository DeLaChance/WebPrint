package nl.webprint.adapter.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Completable;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import nl.webprint.reactivex.configuration.ConfigurationRepository;

public class HttpServerVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);
    
    private Integer port;
    private String hostname;
    
    private ConfigurationRepository configurationRepository;
    
	public HttpServerVerticle() {
	}
	
	@Override
	public void start(final Future<Void> startFuture) {
		this.configurationRepository = ConfigurationRepository.createProxy(this.vertx);
		this.configurationRepository.rxRead("server")
			.subscribe(configuration -> {
				
				LOGGER.info(configuration.encode());
				
				this.port = configuration.getInteger("port");
				this.hostname = configuration.getString("hostname");
				
				LOGGER.info("Starting HTTP server on " + this.hostname + ":" + this.port);
				
				final HttpServerOptions httpServerOptions = new HttpServerOptions()
					.setPort(this.port)
					.setHost(this.hostname);
				
				this.vertx.createHttpServer(httpServerOptions).rxListen();
			}, throwable -> {
				LOGGER.error("Could not start HTTP server ", throwable);
			});
	}	
	
}
