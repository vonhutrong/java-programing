package th_ltm.th3;

public class Converter {
	public static String sexToString(String sex) {
		if ("1".equals(sex)) {
			return Customer.SEX.MALE;
		}
		return Customer.SEX.FEMALE;
	}
	
	public static String stringToSex(String s) {
		if (Customer.SEX.MALE.equals(s)) {
			return "1";
		}
		return "0";
	}
	
	public static String stringToSqlDate(String s) {
		String[] parts = s.split("-");
		return parts[2] + "-" + parts[1] + "-" + parts[0];
	}
	
	public static String sqlDateToString(String s) {
		String[] parts = s.split("-");
		return parts[2] + "-" + parts[1] + "-" + parts[0];
	}
}
