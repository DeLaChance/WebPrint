package nl.webprint.adapter.http;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.stomp.StompServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.stomp.BridgeOptions;
import io.vertx.ext.stomp.StompServer;
import io.vertx.ext.stomp.StompServerHandler;
import nl.webprint.configuration.AddressConfiguration;
import nl.webprint.reactivex.configuration.ConfigurationRepository;

public class HttpServerVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);
    public static final Integer TIME_OUT = 5000;
    public static final String UPLOADS_DIRECTORY = "/var/www/uploads/";
    
    public static final String STOMP_PROTOCOLS = "v10.stomp, v11.stomp, v12.stomp";
    
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

				this.port = configuration.getInteger("port");
				this.hostname = configuration.getString("hostname");
				
				LOGGER.info("Starting HTTP server on " + this.hostname + ":" + this.port);
				
				final HttpServerOptions httpServerOptions = new HttpServerOptions()
					.setPort(this.port)
					.setHost(this.hostname)
					.setWebsocketSubProtocols(STOMP_PROTOCOLS);
				
				final Router router = this.generate();
				this.vertx.getDelegate()
					.createHttpServer(httpServerOptions)
					.requestHandler(router::accept)
					.websocketHandler(createWebSocketHandler())
					.listen();
				
				startFuture.complete();
				
			}, throwable -> {
				LOGGER.error("Could not start HTTP server ", throwable);
				startFuture.fail(throwable);
			});
	}	
	
	private Handler<ServerWebSocket> createWebSocketHandler() {
		final BridgeOptions bridgeOptions = new BridgeOptions()
			.addOutboundPermitted(
					new PermittedOptions()
						.setAddressRegex("notifications.*")
				);
		
		final StompServerOptions stompServerOptions = new StompServerOptions()
			.setPort(-1) // Disable the TCP port
			.setWebsocketBridge(true) 
			.setWebsocketPath("/stomp");
		
		return StompServer.create(vertx.getDelegate(), stompServerOptions)
			.handler(StompServerHandler.create(vertx.getDelegate())
					.bridge(bridgeOptions)
			)
			.webSocketHandler(); 
	}
	
	private Router generate() {
		final Router router = Router.router(this.vertx.getDelegate());
		
		router.route().handler(CorsHandler.create("*")
			.allowedHeader("*")
			.allowedMethod(HttpMethod.GET)
			.allowedMethod(HttpMethod.POST)
			.allowedMethod(HttpMethod.OPTIONS)
			.allowedMethod(HttpMethod.DELETE)
		);
		
		router.route("/").handler(routingContext -> {
		routingContext.reroute("/static/index.html");
		});
		router.route("/static/*").handler(createStaticHandler());
		
		router.route("/api/job").method(HttpMethod.GET).handler(request -> sendToEventBus(request, 
			AddressConfiguration.QUERY_PRINTING_JOB_SERVICE.getAddress()));
		router.route("/api/job/:id").method(HttpMethod.GET).handler(request -> sendToEventBus(request, 
				AddressConfiguration.QUERY_PRINTING_JOB_SERVICE.getAddress()));
		router.route("/api/job/:id").method(HttpMethod.DELETE).handler(request -> sendToEventBus(request, 
				AddressConfiguration.DELETE_PRINTING_JOB_SERVICE.getAddress()));
		router.route("/api/job/:id/start").method(HttpMethod.POST).handler(request -> sendToEventBus(request, 
				AddressConfiguration.START_PRINTING_JOB_SERVICE.getAddress()));
		router.route("/api/job/:id/complete").method(HttpMethod.POST).handler(request -> sendToEventBus(request, 
				AddressConfiguration.COMPLETE_PRINTING_JOB_SERVICE.getAddress()));		
		
		
		router.route("/api/job").method(HttpMethod.POST)
			.handler(BodyHandler.create()
				.setUploadsDirectory(UPLOADS_DIRECTORY)
				.setMergeFormAttributes(true)
			);
		
		router.route("/api/job").method(HttpMethod.POST)
		.handler(request -> sendToEventBus(request, 
			AddressConfiguration.ADD_PRINTING_JOB_SERVICE.getAddress()));		
		
		return router;
	}
	
	private static Handler<RoutingContext> createStaticHandler() {
		final Handler<RoutingContext> staticHandler = StaticHandler.create()
			.setIncludeHidden(false)
			.setWebRoot("webapp");
		
		return staticHandler;
	}	

	private void sendToEventBus(final RoutingContext routingContext, final String address) {

		routingContext.response().setChunked(true);
		
		// Set timeout
		long timerId = this.vertx.setTimer(TIME_OUT, tid -> {
			if( !routingContext.response().ended() ) {
				routingContext.response().setStatusCode(500);
				routingContext.response().end();
			}
		});
		
		final JsonObject payload = createPayload(routingContext);
		
		this.vertx.eventBus().<JsonObject>send(address, payload, response -> {
			if(response.succeeded()) {
				jsonReply(routingContext, response.result().body());
			} else {
				errorReply(routingContext);
			}
			
			this.vertx.cancelTimer(timerId);			
		});
		
	}
	
	private JsonObject createPayload(final RoutingContext routingContext) {
		
		final JsonObject payload = new JsonObject();
		
		LOGGER.debug(routingContext.getBodyAsString());
		
		if( Objects.nonNull(routingContext.getBodyAsString()) ) {
			payload.put("body", routingContext.getBodyAsString());
		}
		
		final JsonArray fileUploads = new JsonArray();
		routingContext.fileUploads()
			.stream()
			.map(FileUpload::from)
			.map(FileUpload::toJson)
			.forEach(fileUpload -> {
				fileUploads.add(fileUpload);
			});
		payload.put("fileUploads", fileUploads);
		
		if( Objects.nonNull(routingContext.pathParams())) {
			routingContext.pathParams()
				.entrySet()
				.stream()
				.forEach(entry -> payload.put(entry.getKey(), entry.getValue()) );
			
		}
		
		if( Objects.nonNull(routingContext.queryParams()) ) {
			routingContext.queryParams()
				.entries()
				.stream()
				.forEach(entry -> payload.put(entry.getKey(), entry.getValue()));
				
		}
		
		return payload;
	}
	
	private void jsonReply(final RoutingContext routingContext, final JsonObject jsonResponse) {
		if( !routingContext.response().ended() ) {
			routingContext.response()
				.setStatusCode(200)
				.putHeader("Content-Type", "application/json; charset=utf-8")
				.end(jsonResponse.encodePrettily(), StandardCharsets.UTF_8.name());
		}
	}
	
	private void errorReply(final RoutingContext routingContext) {
		if( !routingContext.response().ended() ) { 
			routingContext.response()
				.setStatusCode(500)
				.end();
		}
	}
	
}
