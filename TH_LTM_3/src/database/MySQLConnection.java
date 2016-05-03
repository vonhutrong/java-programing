package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection implements ISQLDatabaseConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	protected String subprotocol = "mysql";
	protected String subname = "//localhost";
	protected Connection connection = null;
	protected Statement statement = null;
	
	public MySQLConnection(String databaseName, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		String url = String.format("jdbc:%s:%s/%s", subprotocol, subname, databaseName);
		connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();
	}
	
	public boolean execute(String sql) throws SQLException {
		return statement.execute(sql);
	}

	public ResultSet query(String sql) throws SQLException {
		return statement.executeQuery(sql);
	}
	
	public int update(String sql) throws SQLException {
		return statement.executeUpdate(sql);
	}
	
	public void close() throws SQLException {
		if (null != statement)
			statement.close();
		if (null != connection)
			connection.close();
	}
}
