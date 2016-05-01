<%@page import="th_ltm.th3.Customer"%>
<%@page import="java.util.List"%>
<%@page import="database.CustomerManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("UTF-8");

String HoTenKH = request.getParameter("HoTenKH");

List<Customer> list = CustomerManager.searchCustomersByName(HoTenKH);

request.setAttribute("list", list);

RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
rd.forward(request, response);

%>