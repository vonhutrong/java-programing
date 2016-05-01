<%@page import="database.CustomerManager"%>
<%@page import="th_ltm.th3.Customer"%>
<%@page import="th_ltm.th3.Converter"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="th_ltm.th3.MySQLConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update customer</title>
<%@ include file="head_define.jsp"%>
</head>
<body>
	<%@ include file="nav_bar.jsp"%>
	<%@ include file="page_content_start.jsp"%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<%
		int MaKH = Integer.parseInt(request.getParameter("MaKH"));
		
		Customer c = CustomerManager.getOneCustomer(MaKH);
		String HoTenKH = c.getHoTenKH();
		String SoDienThoai = c.getSoDienThoai();
		String DiaChiKH = c.getDiaChiKH();
		String NgaySinh = c.getNgaySinh();
		String GioiTinh = c.getGioiTinh();
	%>
	<div class="container center_div">
		<div class="well col-md-4 col-md-offset-4">
			<form action="update_customer.jsp" method="post">
				<label><h3>Update customer</h3></label>
				<div class="form-group">
					<label>Name</label>
					<input class="form-control" type="text" value="<%= HoTenKH %>" name="HoTenKH">
				</div>
				<div class="form-group">
					<label>Phone number</label>
					<input class="form-control" type="text" value="<%= SoDienThoai %>" name="SoDienThoai">
				</div>
				<div class="form-group">
					<label>Address</label>
					<input class="form-control" type="text" value="<%= DiaChiKH %>" name="DiaChiKH">
				</div>
				<div class="form-group">
					<label>Date of birth</label>
					<input class="form-control" type="text" value="<%= NgaySinh %>" name="NgaySinh">
				</div>
				<div class="form-group">
					<label>Sex</label>
					<label class="radio-inline">
						<input type="radio" name="GioiTinh" value="Male"
						<% if (Customer.SEX.MALE.equals(GioiTinh))
							out.print("checked='checked'"); %>>
						Male
					</label>
					<label class="radio-inline">
						<input type="radio" name="GioiTinh" value="Female"
						<% if (!Customer.SEX.MALE.equals(GioiTinh))
							out.print("checked='checked'"); %>>
						Female
					</label>
				</div>
				<input type="hidden" name="MaKH" value="<%= MaKH %>">
				<button type="submit" class="btn btn-primary">Update</button>
			</form>
		</div>
	</div>

	<%@ include file="page_content_end.jsp"%>
</body>
</html>