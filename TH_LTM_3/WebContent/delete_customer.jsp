<%@page import="database.CustomerManager"%>
<%@page import="th_ltm.th3.MyTool"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
int MaKH = Integer.parseInt((String)request.getParameter("MaKH"));
CustomerManager.deleteOneCustomer(MaKH);
MyTool.sendRedirect(response, "list_customer.jsp"); 
%>
