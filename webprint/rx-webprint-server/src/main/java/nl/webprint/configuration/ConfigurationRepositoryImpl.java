package nl.webprint.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.reactivex.config.ConfigRetriever;
import io.vertx.core.Future;
import io.vertx.reactivex.core.Vertx;
import io.vertx.serviceproxy.ServiceException;
import nl.webprint.adapter.http.HttpServerVerticle;
import io.vertx.core.json.JsonObject;

public class ConfigurationRepositoryImpl implements ConfigurationRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationRepositoryImpl.class);
	
	private final Vertx vertx;
	
    private ConfigRetriever configRetriever;	
	
	public ConfigurationRepositoryImpl(final io.vertx.core.Vertx vertx) {
		this.vertx = new Vertx(vertx);
		
		final ConfigStoreOptions fileStore = new ConfigStoreOptions()
				.setType("file")
				.setConfig(new JsonObject()
					.put("path", "conf.json"));
		final ConfigRetrieverOptions options = new ConfigRetrieverOptions()
				.addStore(fileStore);
		
		this.configRetriever = ConfigRetriever.create(this.vertx, options);		
	}
	
	@Override
	public void read(String key, Handler<AsyncResult<JsonObject>> resultHandler) {
		this.configRetriever.rxGetConfig()
			.subscribe(json -> {
				
				resultHandler.handle(Future.succeededFuture(json.getJsonObject(key)));
			}, throwable -> {
				ServiceException.fail(1, "key " + key + " does not exist!");
			});
	}

}
