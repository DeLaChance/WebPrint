package nl.webprint.verticle;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import nl.webprint.messaging.PrintingJob;
import nl.webprint.messaging.PrintingJobRequest;
import nl.webprint.messaging.PrintingJobResponse;

public class PrintingJobRepository {
	
	private DatabaseConnector databaseConnector;
	
	private Vertx vertx;
	
	public PrintingJobRepository(final Vertx vertx) {
		this.vertx = vertx;
		this.databaseConnector = new DatabaseConnector(vertx);
	}
	
	public void fetchPrintingJobs(final PrintingJobRequest printingJobRequest, Handler<AsyncResult<PrintingJobResponse>> fetchSQLtask) {
		
		final Future<PrintingJobResponse> future = Future.future();
		future.setHandler(fetchSQLtask);
		
		final Future<String> toSQLStringFuture = Future.future();
		this.convertToSQL(printingJobRequest, toSQLStringFuture);
		
		toSQLStringFuture.compose(sqlQuery -> {
			// Delegate query to DB connector
			final Future<ResultSet> resultSetFuture = Future.future();
			this.databaseConnector.executeQuery(sqlQuery, resultSetFuture.completer());
			return resultSetFuture;
		}).compose(resultSet -> {
			// Convert into response			
			future.handle(Future.succeededFuture(this.convert(resultSet)));
		}, future);
	}	
	
	private void convertToSQL(final PrintingJobRequest printingJobRequest, final Handler<AsyncResult<String>> aHandler) {
	
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT p.id, p.name FROM PRINTING_JOB p");
		
		if( Objects.nonNull(printingJobRequest.getIdentifier()) ) {
			stringBuilder.append(" WHERE p.id = " + printingJobRequest.getIdentifier());
		}
		
		aHandler.handle(Future.succeededFuture(stringBuilder.toString()));
	}
	
	private PrintingJobResponse convert(final ResultSet resultSet) {
		final List<PrintingJob> printingJobs = resultSet.getRows().stream().map(row -> {
			return PrintingJob.builder()
				.id(row.getString("id"))
				.name(row.getString("name"))
				.created(row.getLong("createdDate"))
				.completed(row.getLong("completedDate"))
				.fileContents(null)
				.started(row.getLong("startedDate"))
				.build();
		}).collect(Collectors.toList());
		
		return PrintingJobResponse.builder()
			.printingJobs(printingJobs)
			.build();
	}
	
	
}
