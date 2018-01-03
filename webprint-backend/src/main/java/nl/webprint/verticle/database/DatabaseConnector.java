package nl.webprint.verticle.database;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class DatabaseConnector {	
	
	private SQLClient client;
	
	public DatabaseConnector(final Vertx vertx) {
	
		final JsonObject config = new JsonObject()
			.put("url", "jdbc:sqlite:/opt/sqlite3/webprint.sqlite")
			.put("driver_class", "org.sqlite.JDBC");
			
		this.client = JDBCClient.createNonShared(vertx, config);
	}
	
	public void executeQuery(final String sqlQuery, Handler<AsyncResult<ResultSet>> aHandler) {
		final Future<ResultSet> resultSetFuture = Future.future();		
		resultSetFuture.setHandler(aHandler);
		
		final Future<SQLConnection> connectionFuture = Future.future();
		this.client.getConnection(connectionFuture);
		
		connectionFuture.compose(connection -> {
			System.out.println("Running query: " + sqlQuery);
			this.runQuery(connection, sqlQuery, resultSetFuture.completer());
		}, resultSetFuture);
		
	}
	
	private void runQuery(final SQLConnection connection, final String sqlQuery, final Handler<AsyncResult<ResultSet>> aHandler) {
		connection.query(sqlQuery, aHandler);
	}

}
