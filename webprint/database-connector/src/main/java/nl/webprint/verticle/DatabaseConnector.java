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
			System.out.println("Running query: " + sqlQuery);
			this.runQuery(connection, sqlQuery, resultSetFuture.completer());
		}, resultSetFuture);
		
	}
	
	public void executeDeleteQuery(final String sqlQuery, final Handler<AsyncResult<Boolean>> aHandler) {
		final Future<Boolean> deletedFuture = Future.future();
		deletedFuture.setHandler(aHandler);
		
		final Future<SQLConnection> connectionFuture = Future.future();
		this.client.getConnection(connectionFuture);
		
		connectionFuture.compose(connection -> {
			System.out.println("Running deletion query: " + sqlQuery);
			final Future<UpdateResult> intermediateFuture = Future.future();
			this.runUpdate(connection, sqlQuery, intermediateFuture.completer());
			return intermediateFuture;
		}).compose(updateResult -> {
			System.out.println("updateResult.getUpdated()=" + updateResult.getUpdated());
			final boolean wasDeleted = (updateResult.getUpdated() == 1);
			deletedFuture.complete(wasDeleted);
		}, deletedFuture)
		.otherwise(exception -> {
			exception.printStackTrace();
			return false;
		});
	}
	
	private void runQuery(final SQLConnection connection, final String sqlQuery, final Handler<AsyncResult<ResultSet>> aHandler) {
		connection.query(sqlQuery, aHandler);
	}
	
	private void runUpdate(final SQLConnection connection, final String sqlQuery, final Handler<AsyncResult<UpdateResult>> aHandler) {
		connection.update(sqlQuery, aHandler);
	}

}
