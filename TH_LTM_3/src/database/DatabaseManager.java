package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	private static MySQLConnection connection = null;
	private static final String DATABASE_NAME = "dulieu";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static boolean isConnected() {
		return null != connection;
	}
	
	public static boolean connectDatabase(String databaseName) {
		try {
			connection = new MySQLConnection(databaseName, USERNAME, PASSWORD);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @return return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing or (3) -1 when error
	 */
	public static int executeInsert(String sql) {
		if (!DatabaseManager.isConnected()) {
			DatabaseManager.connectDatabase(DATABASE_NAME);
		}
		try {
			return connection.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int executeUpdate(String sql) {
		return executeInsert(sql);
	}
	
	public static int executeDelete(String sql) {
		return executeInsert(sql);
	}
	
	/**
	 * 
	 * @return return ResultSet or null if error
	 */
	public static ResultSet executeQuery(String sql) {
		if (!DatabaseManager.isConnected()) {
			DatabaseManager.connectDatabase(DATABASE_NAME);
		}
		try {
			return connection.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean disconnectDatabase() {
		if (null == connection)
			return true;
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
