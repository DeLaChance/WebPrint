package nl.webprint.verticle;

import java.util.Objects;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import nl.webprint.util.MessageSender;

public class RouterGenerator {
	
	public static final int TIME_OUT = 2000;

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
			RouterGenerator.handlePrintingJobRequest(rc, null);
		});

		// Request printing job with specific identifier
		restAPI.route(HttpMethod.GET, "/job/:id").handler(rc -> {
			final String identifier = rc.request().getParam("id");
			RouterGenerator.handlePrintingJobRequest(rc, identifier);
		});

		return restAPI;
	}
	
	private static void handlePrintingJobRequest(final RoutingContext rc, final String identifier) {
		rc.response().setChunked(true);
		
		final Future<Void> finalFuture = Future.future();
		
		final Future<Message<String>> future = Future.future();
		if( Objects.nonNull(identifier) ) { 
			MessageSender.sendPrintingJobRequest(rc.vertx(), identifier, future.completer());
		} else {
			MessageSender.sendPrintingJobRequest(rc.vertx(), future.completer());
		}

		long timerId = rc.vertx().setTimer(TIME_OUT, tid -> {
			if( !rc.response().ended() ) {
				rc.response().end();
			}
		});
		
		future.compose(message -> {
			rc.response().write(message.body());
			rc.response().end();
			rc.vertx().cancelTimer(timerId);
			finalFuture.complete();
		}, finalFuture);		
	}
	
	private static Handler<RoutingContext> createStaticHandler() {
		final Handler<RoutingContext> staticHandler = StaticHandler.create()
			.setIncludeHidden(false)
			.setWebRoot("webapp");
		
		return staticHandler;
	}

}
