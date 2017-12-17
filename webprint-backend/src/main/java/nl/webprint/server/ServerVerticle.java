package nl.webprint.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;

public class ServerVerticle extends AbstractVerticle {

	public ServerVerticle() {
		super();
	}
	
	@Override
	public void start() {
		final HttpServerOptions options = this.createOptions();
		final HttpServer httpServer = this.vertx.createHttpServer(options);
		final int portNumber = 8081;
		
		httpServer.requestHandler(request -> {
		
		  // This handler gets called for each request that arrives on the server
		  HttpServerResponse response = request.response();
		  response.putHeader("content-type", "text/plain");
		
		  // Write to the response and end it
		  response.end("Hello World!");
		});
		
		httpServer.listen(8081);
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
