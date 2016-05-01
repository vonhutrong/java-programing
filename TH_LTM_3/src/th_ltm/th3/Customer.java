package th_ltm.th3;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	public interface SEX {
	    String MALE = "Male";
	    String FEMALE = "Female";
	}
	private int MaKH;
	private String HoTenKH;
	private String SoDienThoai;
	private String DiaChiKH;
	private String NgaySinh;
	private String GioiTinh;
	
	public static Customer fetch(ResultSet rs) throws NumberFormatException, SQLException {
		Customer ct = new Customer();
		ct.setMaKH(Integer.parseInt(rs.getString("MaKH")));
		ct.setHoTenKH(rs.getString("HoTenKH"));
		ct.setSoDienThoai(rs.getString("SoDienThoai"));
		ct.setDiaChiKH(rs.getString("DiaChiKH"));
		ct.setNgaySinh(Converter.sqlDateToString(rs.getString("NgaySinh")));
		ct.setGioiTinh(Converter.sexToString(rs.getString("GioiTinh")));
		return ct;
	}

	public int getMaKH() {
		return MaKH;
	}

	public void setMaKH(int maKH) {
		MaKH = maKH;
	}

	public String getHoTenKH() {
		return HoTenKH;
	}

	public void setHoTenKH(String hoTenKH) {
		HoTenKH = hoTenKH;
	}

	public String getSoDienThoai() {
		return SoDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}

	public String getDiaChiKH() {
		return DiaChiKH;
	}

	public void setDiaChiKH(String diaChiKH) {
		DiaChiKH = diaChiKH;
	}

	public String getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

}
