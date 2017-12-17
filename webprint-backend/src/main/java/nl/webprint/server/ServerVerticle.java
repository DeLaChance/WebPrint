package nl.webprint.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class ServerVerticle extends AbstractVerticle {

	public ServerVerticle() {
		super();
	}
	
	@Override
	public void start() {
		final HttpServerOptions options = this.createOptions();
		final HttpServer httpServer = this.vertx.createHttpServer(options);
		final int portNumber = 8081;
		
		final Router router = RouterGenerator.generate(this.vertx);
		
		httpServer.requestHandler(router::accept).listen(portNumber);
	}

	@Override
	public void stop() {
	
	}
	
	private HttpServerOptions createOptions() {
		final HttpServerOptions options = new HttpServerOptions()
			.setLogActivity(true);
		
		return options;
	}

}
