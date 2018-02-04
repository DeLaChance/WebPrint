package nl.webprint.configuration;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Maintains the runtime configuration of this app.
 * 
 * @author lucien
 *
 */
@ProxyGen
@VertxGen
public interface ConfigurationRepository {

	String EVENT_BUS_ADDRESS = AddressConfiguration.CONFIGURATION_REPOSITORY.getAddress();
	
	static ConfigurationRepository create(final Vertx vertx) {
		return new ConfigurationRepositoryImpl(vertx);
	}
	
	static ConfigurationRepository createProxy(final Vertx vertx) {
		return new ConfigurationRepositoryVertxEBProxy(vertx, EVENT_BUS_ADDRESS);
	}
	
	/**
	 * Reads an item from the configuration store and parses it to a JSON object.
	 * 
	 * @param key
	 * @param jsonObject
	 */
	void read(final String key, Handler<AsyncResult<JsonObject>> resultHandler);
	
}
