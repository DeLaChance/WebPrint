package nl.webprint.verticle.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import nl.webprint.util.Runner;

public class ServerVerticle extends AbstractVerticle {

	public static Integer HTTP_SERVER_PORT = 8081;

	public ServerVerticle() {
		super();
	}

	public static void main(final String[] args) {
		Runner.run(ServerVerticle.class);
	}

	@Override
	public void start() {
		final HttpServerOptions options = this.createOptions();
		final HttpServer httpServer = this.vertx.createHttpServer(options);

		final Router router = RouterGenerator.generate(this.vertx);
		
		httpServer.requestHandler(router::accept).listen(HTTP_SERVER_PORT);
		
		System.out.println("Started ServerVerticle");
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
