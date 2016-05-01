<%@page import="database.CustomerManager"%>
<%@page import="th_ltm.th3.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("UTF-8");

List<Customer> list = CustomerManager.searchCustomersByName("");

request.setAttribute("list", list);

RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
rd.forward(request, response);

%>