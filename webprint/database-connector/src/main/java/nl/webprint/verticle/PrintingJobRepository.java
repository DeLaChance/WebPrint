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
import nl.webprint.messaging.PrintingJobDeletionRequest;
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
			this.databaseConnector.executeSelectQuery(sqlQuery, resultSetFuture.completer());
			return resultSetFuture;
		}).compose(resultSet -> {
			// Convert into response			
			future.handle(Future.succeededFuture(this.convert(resultSet)));
		}, future);
	}	


	public void deletePrintingJob(final PrintingJobDeletionRequest printingJobDeletionRequest,
			final Handler<AsyncResult<Boolean>> deleteInDBTask) {

		final Future<Boolean> future = Future.future();
		future.setHandler(deleteInDBTask);
		
		final Future<String> toSQLStringFuture = Future.future();
		this.convertToSQL(printingJobDeletionRequest, toSQLStringFuture);
		
		toSQLStringFuture.compose(sqlQuery -> {
			// Delegate query to DB connector
			this.databaseConnector.executeDeleteQuery(sqlQuery, future.completer());
			future.complete();
		}, future);
	}
	
	private void convertToSQL(final PrintingJobRequest request, final Handler<AsyncResult<String>> aHandler) {
	
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT p.id, p.name, p.createdDate, p.completedDate, p.startedDate FROM PRINTING_JOB p");
		
		if( Objects.nonNull(request.getIdentifier()) ) {
			stringBuilder.append(" WHERE p.id = " + request.getIdentifier());
		}
		
		aHandler.handle(Future.succeededFuture(stringBuilder.toString()));
	}

	private void convertToSQL(PrintingJobDeletionRequest deletionRequest, final Handler<AsyncResult<String>> aHandler) {
		
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("DELETE FROM PRINTING_JOB WHERE id = " + deletionRequest.getIdentifier());
		
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
