package th_ltm.th3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	protected String user;
	protected String pass;
	protected Connection conn = null;
	protected Statement stmt = null;

	public MySQLConnection(String dbName, String user, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL + dbName, user, pass);
		stmt = conn.createStatement();
	}

	public ResultSet query(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}

	public void execute(String sql) throws SQLException {
		stmt.execute(sql);
	}
	
	public void close() throws SQLException {
		if (null == conn || null == stmt)
			return;
		stmt.close();
		conn.close();
	}
	
	/*
	public static void main(String[] args) {
		try {
			MySQLConnection conn = new MySQLConnection("dulieu", "root", "");
			System.out.println("connected to database");
			
			conn.stmt.executeUpdate("insert into khachhang (HoTenKH, SoDienThoai, DiaChiKH, NgaySinh, GioiTinh)  values('Duyên', '' , '', '1996/01/02', 0 )");
			
			System.out.println("successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/

}
