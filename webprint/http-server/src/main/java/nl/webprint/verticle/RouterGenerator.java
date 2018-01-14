package nl.webprint.verticle;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import nl.webprint.messaging.PrintingJob;
import nl.webprint.util.MessageSender;

public class RouterGenerator {
	
	public static final int TIME_OUT = 2000;
	private static final String UPLOADS_DIRECTORY = "/var/www/uploads/";

	public static Router generate(final Vertx vertx) {
		final Router router = Router.router(vertx);

	    router.route().handler(CorsHandler.create("*")
	      .allowedMethod(HttpMethod.GET)
	      .allowedMethod(HttpMethod.POST)
	      .allowedMethod(HttpMethod.OPTIONS)
	    );
		
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
		
		// Delete a printing job
		restAPI.route(HttpMethod.DELETE, "/job").handler(rc -> {
			final String identifier = rc.request().getParam("id");
			RouterGenerator.handePrintingJobDeletion(rc, identifier);
		});

		// Store all file uploads under the uploads directory
		restAPI.route(HttpMethod.POST, "/job")
			.handler(BodyHandler.create()
				.setUploadsDirectory(UPLOADS_DIRECTORY)
				.setMergeFormAttributes(true));		
		
		restAPI.route(HttpMethod.POST, "/job").handler(rc -> {
			
			RouterGenerator.handlePrintingJobCreation(rc);			
		});		

		return restAPI;
	}
	
	private static void handlePrintingJobCreation(final RoutingContext rc) {
		
		rc.response().setChunked(true);
		long timerId = rc.vertx().setTimer(5*TIME_OUT, tid -> {
			if( !rc.response().ended() ) {
				rc.response().setStatusCode(500);
				rc.response().end();
			}
		});			

		final List<FileUpload> fileUploads = new ArrayList<>();
		fileUploads.addAll(rc.fileUploads());		
		
		if( fileUploads.size() > 0 ) {		
			final Future<Void> finalFuture = Future.future();
			
			final Future<Buffer> readFileFuture = Future.future();			
			RouterGenerator.readFile(rc.vertx(), fileUploads.get(0), readFileFuture);
					
			readFileFuture.compose(buffer -> {
				final Future<Message<String>> sendMessageFuture = Future.future();
				
				final String identifier = UUID.randomUUID().toString();
				final String fileContents = buffer.toString();
				
				final PrintingJob printingJob = PrintingJob.builder()
					.completed(null)
					.created(Instant.now().getEpochSecond())
					.started(null)
					.fileContents(fileContents)
					.id(identifier)
					.name(fileUploads.get(0).fileName())
					.started(null)
					.build();
				
				MessageSender.sendPrintingJobCreation(rc.vertx(), printingJob, sendMessageFuture.completer());
				return sendMessageFuture;
			}).compose(message -> {
				rc.response().write(message.body());
				rc.response().setStatusCode(200);
				rc.response().end();
				rc.vertx().cancelTimer(timerId);
				finalFuture.complete();			
			}, finalFuture); 
		}
	}
	
	private static void readFile(final Vertx vertx, final FileUpload fileUpload, final Future<Buffer> futureBuffer) {
		final FileSystem fileSystem = vertx.fileSystem();
		final String uploadedFileName = fileUpload.uploadedFileName();

		final Future<Buffer> readFileFuture = Future.future();
		fileSystem.readFile(uploadedFileName, readFileFuture.completer());
		
		readFileFuture.compose(buffer -> {
			futureBuffer.complete(buffer);
		}, futureBuffer)
		.otherwise(exception -> {
			exception.printStackTrace();
			return null;
		});
	
	}
	
	private static void handePrintingJobDeletion(final RoutingContext rc,final String identifier) {
		final Future<Void> finalFuture = Future.future();
		
		final Future<Message<String>> future = Future.future();
		MessageSender.sendPrintingJobDeletion(rc.vertx(), identifier, future.completer());

		rc.response().setChunked(true);

		long timerId = rc.vertx().setTimer(TIME_OUT, tid -> {
			if( !rc.response().ended() ) {
				rc.response().setStatusCode(500);
				rc.response().end();
			}
		});
		
		future.compose(message -> {
			rc.response().write(message.body());
			rc.response().setStatusCode(200);
			rc.response().end();
			rc.vertx().cancelTimer(timerId);
			finalFuture.complete();
		}, finalFuture);
	}

	private static void handlePrintingJobRequest(final RoutingContext rc, final String identifier) {
		final Future<Void> finalFuture = Future.future();
		
		final Future<Message<String>> future = Future.future();
		if( Objects.nonNull(identifier) ) { 
			MessageSender.sendPrintingJobRequest(rc.vertx(), identifier, future.completer());
		} else {
			MessageSender.sendPrintingJobRequest(rc.vertx(), future.completer());
		}
		
		rc.response().setChunked(true);

		long timerId = rc.vertx().setTimer(TIME_OUT, tid -> {
			if( !rc.response().ended() ) {
				rc.response().setStatusCode(500);
				rc.response().end();
			}
		});
		
		future.compose(message -> {
			rc.response().write(message.body());
			rc.response().setStatusCode(200);			
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
