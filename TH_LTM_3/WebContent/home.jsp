<%@page import="th_ltm.th3.Customer"%>
<%@page import="java.util.List"%>
<%@page import="database.CustomerManager"%>
<%@page import="database.MySQLConnection"%>
<%@page import="th_ltm.th3.Converter"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List customer</title>
<%@ include file="head_define.jsp"%>
</head>
<body>
	<%@ include file="nav_bar.jsp"%>
	<%@ include file="page_content_start.jsp"%>
	<% request.setCharacterEncoding("UTF-8"); %>

	<table class="table table-striped">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Phone number</th>
			<th>Address</th>
			<th>Date of birth</th>
			<th>Sex</th>
			<th>Actions</th>
		</tr>
		<%
			List<Customer> list = (List<Customer>) request.getAttribute("list");
			if (null != list) {
				for (int i = 0; i < list.size(); ++i) {
					Customer c = list.get(i);
		%>
		<tr>
			<th scope="row"><%=c.getMaKH()%></th>
			<td><%=c.getHoTenKH()%></td>
			<td><%=c.getSoDienThoai()%></td>
			<td><%=c.getDiaChiKH()%></td>
			<td><%=c.getNgaySinh()%></td>
			<td><%=c.getGioiTinh()%></td>
			<td><a role="button" class="btn btn-info btn-xs"
				href="update_customer_form.jsp?MaKH=<%=c.getMaKH()%>">edit</a>
				<a role="button" class="btn btn-danger btn-xs"
				href="delete_customer.jsp?MaKH=<%=c.getMaKH()%>">delete</a></td>
		</tr>
		<%
			}
			}
		%>
	</table>

	<%@ include file="page_content_end.jsp"%>
</body>
</html>