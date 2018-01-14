package nl.webprint.verticle;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.UpdateResult;

public class DatabaseConnector {	
	
	private SQLClient client;
	
	public DatabaseConnector(final Vertx vertx) {
	
		final JsonObject config = new JsonObject()
			.put("url", "jdbc:sqlite:/opt/sqlite3/webprint.sqlite")
			.put("driver_class", "org.sqlite.JDBC");
			
		this.client = JDBCClient.createNonShared(vertx, config);
	}
	
	public void executeSelectQuery(final String sqlQuery, Handler<AsyncResult<ResultSet>> aHandler) {
		final Future<ResultSet> resultSetFuture = Future.future();		
		resultSetFuture.setHandler(aHandler);
		
		final Future<SQLConnection> connectionFuture = Future.future();
		this.client.getConnection(connectionFuture);
		
		connectionFuture.compose(connection -> {
			this.runQuery(connection, sqlQuery, resultSetFuture.completer());
		}, resultSetFuture);
		
	}
	
	public void executeDeleteQuery(final String sqlQuery, final Handler<AsyncResult<Boolean>> aHandler) {
		final Future<Boolean> deletedFuture = Future.future();
		deletedFuture.setHandler(aHandler);
		
		final Future<SQLConnection> connectionFuture = Future.future();
		this.client.getConnection(connectionFuture);
		
		connectionFuture.compose(connection -> {
			final Future<UpdateResult> intermediateFuture = Future.future();
			this.runUpdate(connection, sqlQuery, intermediateFuture.completer());
			return intermediateFuture;
		}).compose(updateResult -> {
			final boolean wasDeleted = (updateResult.getUpdated() == 1);
			deletedFuture.complete(wasDeleted);
		}, deletedFuture)
		.otherwise(exception -> {
			exception.printStackTrace();
			return false;
		});
	}

	public void executeInsertQuery(String sqlQuery, Future<Boolean> insertInDBFuture) {
		
		final Future<SQLConnection> connectionFuture = Future.future();
		this.client.getConnection(connectionFuture);
		
		connectionFuture.compose(connection -> {
			final Future<UpdateResult> intermediateFuture = Future.future();
			this.runUpdate(connection, sqlQuery, intermediateFuture.completer());
			return intermediateFuture;
		}).compose(insertionResult -> {
			final boolean wasInserted = (insertionResult.getUpdated() == 1);
			insertInDBFuture.complete(wasInserted);
		}, insertInDBFuture)
		.otherwise(exception -> {
			exception.printStackTrace();
			return false;
		});
		
	}
	
	private void runQuery(final SQLConnection connection, final String sqlQuery, final Handler<AsyncResult<ResultSet>> aHandler) {
		System.out.println("Running query: " + sqlQuery);
		
		connection.query(sqlQuery, aHandler);
	}
	
	private void runUpdate(final SQLConnection connection, final String sqlQuery, final Handler<AsyncResult<UpdateResult>> aHandler) {
		System.out.println("Running query: " + sqlQuery);
		
		connection.update(sqlQuery, aHandler);
	}
}
