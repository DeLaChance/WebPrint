package nl.webprint.verticle.database;

import java.util.Objects;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.sql.ResultSet;
import nl.webprint.messaging.PrintingJobRequest;

public class PrintingJobRepository {
	
	private DatabaseConnector databaseConnector;
	
	private Vertx vertx;
	
	public PrintingJobRepository(final Vertx vertx) {
		this.vertx = vertx;
		this.databaseConnector = new DatabaseConnector(vertx);
	}
	
	public void fetchPrintingJobs(final PrintingJobRequest printingJobRequest, Handler<AsyncResult<ResultSet>> fetchSQLtask) {
		
		final Future<ResultSet> future = Future.future();
		future.setHandler(fetchSQLtask);
		
		final Future<String> toSQLStringFuture = Future.future();
		this.convertToSQL(printingJobRequest, toSQLStringFuture);
		
		toSQLStringFuture.compose(sqlQuery -> {
			final Future<ResultSet> toResultSetFuture = Future.future();
			this.databaseConnector.executeQuery(sqlQuery, toResultSetFuture);
			return toResultSetFuture;
		});
	}
	
	
	private void convertToSQL(final PrintingJobRequest printingJobRequest, final Handler<AsyncResult<String>> aHandler) {
	
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT p.id, p.name FROM PRINTING_JOB p");
		
		if( Objects.nonNull(printingJobRequest.getIdentifier()) ) {
			stringBuilder.append(" WHERE p.id = " + printingJobRequest.getIdentifier());
		}
		
		aHandler.handle(Future.succeededFuture(stringBuilder.toString()));
	}

}
