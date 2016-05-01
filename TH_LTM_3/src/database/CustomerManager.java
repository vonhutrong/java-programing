package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import th_ltm.th3.Converter;
import th_ltm.th3.Customer;

public class CustomerManager {

	public static boolean addOneCustomer(Customer customer) {
		String stmt = "INSERT INTO `khachhang`"
				+ "(`HoTenKH`, `SoDienThoai`, `DiaChiKH`, `NgaySinh`, `GioiTinh`)"
				+ " VALUES ('%s','%s','%s','%s',%s)";
		String sql = String.format(stmt, customer.getHoTenKH(),
				customer.getSoDienThoai(), customer.getDiaChiKH(),
				Converter.stringToSqlDate(customer.getNgaySinh()),
				Converter.stringToSex(customer.getGioiTinh()));
		;
		return DatabaseManager.executeInsert(sql) > 0 ? true : false;
	}

	public static Customer getOneCustomer(int MaKH) {
		String stmt = "SELECT * FROM `khachhang` WHERE `MaKH`=%s";
		String sql = String.format(stmt, MaKH + "");
		ResultSet rs = DatabaseManager.executeQuery(sql);
		Customer c = null;
		try {
			if (rs.next()) {
				c = Customer.fetch(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static boolean updateOneCustomer(Customer customer) {
		String stmt = "UPDATE `khachhang`"
				+ " SET `HoTenKH`='%s',`SoDienThoai`='%s',`DiaChiKH`='%s',`NgaySinh`='%s',`GioiTinh`=%s"
				+ " WHERE `MaKH`=%s";
		String sql = String.format(stmt, customer.getHoTenKH(),
				customer.getSoDienThoai(), customer.getDiaChiKH(),
				Converter.stringToSqlDate(customer.getNgaySinh()),
				Converter.stringToSex(customer.getGioiTinh()),
				customer.getMaKH());
		return DatabaseManager.executeUpdate(sql) > 0 ? true : false;
	}

	public static boolean deleteOneCustomer(int MaKH) {
		String stmt = "DELETE FROM `khachhang` WHERE `MaKH`=%s";
		String sql = String.format(stmt, MaKH + "");
		return DatabaseManager.executeUpdate(sql) > 0 ? true : false;
	}

	public static List<Customer> searchCustomersByName(String HoTenKH) {
		String sql;
		if (null == HoTenKH || HoTenKH.trim().length() == 0) {
			sql = "SELECT * FROM `khachhang`";
		} else {
			sql = "SELECT * FROM `khachhang` WHERE `HoTenKH` LIKE '%" + HoTenKH + "%'";
		}

		ResultSet rs = DatabaseManager.executeQuery(sql);

		List<Customer> list = new ArrayList<Customer>();
		try {
			while (rs.next()) {
				list.add(Customer.fetch(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size() > 0 ? list : null;
	}
}
