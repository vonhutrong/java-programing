package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author trongvn13
 *
 */
public interface ISQLDatabaseConnection {
	boolean execute(String sql) throws SQLException;
	ResultSet query(String sql) throws SQLException;
	int update(String sql) throws SQLException;
	void close() throws SQLException;
}
