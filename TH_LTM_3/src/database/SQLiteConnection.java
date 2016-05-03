package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author trongvn13
 *
 */
public class SQLiteConnection implements ISQLDatabaseConnection {
	static final String JDBC_DRIVER = "org.sqlite.JDBC";

	protected Connection connection = null;
	protected Statement statement = null;

	public SQLiteConnection(String dbName)
			throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
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
