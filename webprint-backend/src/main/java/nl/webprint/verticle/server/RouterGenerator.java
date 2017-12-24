package nl.webprint.verticle.server;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

public class RouterGenerator {

	public static Router generate(final Vertx vertx) {
		final Router router = Router.router(vertx);

		router.route("/").handler(routingContext -> {
			routingContext.reroute("/static/index.html");
		});
		router.route("/static/*").handler(createStaticHandler());
		router.mountSubRouter("/api", generateRestApi(vertx));
		
		return router;
	}

	public static Router generateRestApi(final Vertx vertx) {
		final Router restAPI = Router.router(vertx);

		// Request all printing jobs
		restAPI.route(HttpMethod.GET, "/job").handler(rc -> {
			MessageSender.sendPrintingJobRequest(vertx);
			rc.response().end();
		});

		// Request printing job with specific identifier
		restAPI.route(HttpMethod.GET, "/job/:id").handler(rc -> {
			final String identifier = rc.request().getParam("id");
			MessageSender.sendPrintingJobRequest(vertx, identifier);
			rc.response().end();
		});

		return restAPI;
	}
	
	private static Handler<RoutingContext> createStaticHandler() {
		final Handler<RoutingContext> staticHandler = StaticHandler.create()
			.setIncludeHidden(false)
			.setWebRoot("webapp");
		
		return staticHandler;
	}

}
