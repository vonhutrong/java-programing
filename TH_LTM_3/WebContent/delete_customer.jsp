<%@page import="th_ltm.th3.MyTool"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="th_ltm.th3.MySQLConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int MaHK = Integer.parseInt((String)request.getParameter("MaKH"));
MySQLConnection connection = new MySQLConnection("dulieu", "root", "");
connection.execute("delete from khachhang where MaKH=" + MaHK);
MyTool.sendRedirect(response, "list_customer.jsp"); 
%>
