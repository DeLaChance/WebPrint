package nl.webprint.printing;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.Message;
import nl.webprint.reactivex.printing.PrintingJobRepository;
import nl.webprint.util.UUIDConverter;
import nl.webprint.configuration.AddressConfiguration;
import nl.webprint.adapter.http.FileUpload;

public class PrintingVerticle extends AbstractVerticle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PrintingVerticle.class);
	
	private PrintingJobRepository printingJobRepository;
	
	public PrintingVerticle() {
	}
	
	@Override
	public void start(final Future<Void> startFuture) {
		this.printingJobRepository = PrintingJobRepository.createProxy(this.vertx);
		
		// QUERY
		this.vertx.eventBus().<JsonObject>consumer(AddressConfiguration.QUERY_PRINTING_JOB_SERVICE.getAddress(), message -> {
			final Optional<String> optionalString = Optional.ofNullable(message)
				.map(Message::body)
				.map(obj -> obj.getString("id"));
			
			if( optionalString.isPresent() ) {
				// Identifier present
				final Optional<PrintingJobIdentifier> optionalUUID = optionalString.filter(UUIDConverter::canConvert)
					.map(UUIDConverter::convert).map(PrintingJobIdentifier::from);
				if( optionalUUID.isPresent() ) {
					// Valid identifier, query for the matching printing job
					this.printingJobRepository.rxFindById(optionalUUID.get())
						.subscribe(
							printingJob -> {
								message.reply(printingJob.toJson());
							},
							throwable -> {
								message.fail(500, "Server error");
							}
						);
				} else {
					// Invalid identifier, error
					message.fail(304, "Invalid identifier"); 
				}
			} else {
				// No identifier-filter present, query all
				this.printingJobRepository.rxFindAll()
					.subscribe(
						printingJobs -> {
							final JsonObject printingJobsArray = new JsonObject();
							printingJobsArray.put("jobs", printingJobs.stream()
								.map(PrintingJob::toJson)
								.collect(Collectors.toList())
							);
							message.reply(printingJobsArray);
						},
						throwable -> {
							message.fail(500, "Server error");
						}
					);
			} 
			
		});
		
		// DELETION
		this.vertx.eventBus().<JsonObject>consumer(AddressConfiguration.DELETE_PRINTING_JOB_SERVICE.getAddress(), message -> {
			LOGGER.debug("Deletion: " + message.body().encode());
			
			final Optional<PrintingJobIdentifier> optionalIdentifier = Optional.ofNullable(message)
					.map(Message::body)
					.map(obj -> obj.getString("id"))
					.filter(UUIDConverter::canConvert)
					.map(UUIDConverter::convert)
					.map(PrintingJobIdentifier::from);
			
			if( optionalIdentifier.isPresent() ) {
				this.printingJobRepository.rxDelete(optionalIdentifier.get())
					.subscribe(
						() -> message.reply(optionalIdentifier.get().toJson()),
						throwable -> message.fail(500, "Server error")
					);
			} else {
				// Invalid or absent identifier, reply as failure
				message.fail(304, "Absent identifier");
			}
		});
		
		// INSERTION
		this.vertx.eventBus().<JsonObject>consumer(AddressConfiguration.ADD_PRINTING_JOB_SERVICE.getAddress(), message -> {
			LOGGER.debug("Insertion: " + message.body().encode());
			
			final Optional<FileUpload> optional = Optional.ofNullable(message)
				.map(Message::body)
				.map(obj -> obj.getJsonArray("fileUploads"))
				.filter(not(JsonArray::isEmpty))
				.map(obj -> obj.getJsonObject(0))
				.map(obj -> new FileUpload(obj));
			
			if( optional.isPresent() ) {
				this.printingJobRepository.rxAdd(optional.get())
					.subscribe(
						printingJob -> message.reply(printingJob.toJson()),
						throwable -> message.fail(500, "Server error")
					);
			} else {
				message.fail(304, "No file uploaded");
			}
		});
		
		// UPDATE
		this.vertx.eventBus().<JsonObject>consumer(AddressConfiguration.START_PRINTING_JOB_SERVICE.getAddress(), message -> {
			LOGGER.debug("Update (start): " + message.body().encode());
			
			final Optional<PrintingJobIdentifier> optionalIdentifier = Optional.ofNullable(message)
					.map(Message::body)
					.map(obj -> obj.getString("id"))
					.filter(UUIDConverter::canConvert)
					.map(UUIDConverter::convert)
					.map(PrintingJobIdentifier::from);
			
			if( optionalIdentifier.isPresent() ) {
				this.printingJobRepository.rxFindByIdOrError(optionalIdentifier.get())
					.flatMap(printingJob -> 
						printingJob.start()
					)
					.subscribe(
						printingJob -> {
							this.vertx.eventBus().publish("notifications.printing-job", printingJob.toJson());
							message.reply(printingJob.toJson());
						},
						throwable -> {
							message.fail(404, "Could not be found");
						}
					);
			}
		});
		
		this.vertx.eventBus().<JsonObject>consumer(AddressConfiguration.COMPLETE_PRINTING_JOB_SERVICE.getAddress(), message -> {
			LOGGER.debug("Update (complete): " + message.body().encode());
			
			final Optional<PrintingJobIdentifier> optionalIdentifier = Optional.ofNullable(message)
					.map(Message::body)
					.map(obj -> obj.getString("id"))
					.filter(UUIDConverter::canConvert)
					.map(UUIDConverter::convert)
					.map(PrintingJobIdentifier::from);
			
			if( optionalIdentifier.isPresent() ) {
				this.printingJobRepository.rxFindByIdOrError(optionalIdentifier.get())
					.flatMap(printingJob -> 
						printingJob.complete()
					)
					.subscribe(
						printingJob -> {
							this.vertx.eventBus().publish("notifications.printing-job", printingJob.toJson());
							message.reply(printingJob.toJson());
						},
						throwable -> {
							message.fail(404, "Could not be found");
						}
					);
			}			
		});		
		
		startFuture.complete();
	}

	public static <T> Predicate<T> not(Predicate<T> t) {
	    return t.negate();
	}

}
