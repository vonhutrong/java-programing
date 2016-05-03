<%@page import="database.CustomerManager"%>
<%@page import="th_ltm.th3.Customer"%>
<%@page import="th_ltm.th3.Converter"%>
<%@page import="th_ltm.th3.MyTool"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
request.setCharacterEncoding("UTF-8");

int MaKH = Integer.parseInt(request.getParameter("MaKH"));

Customer c = new Customer();
c.setMaKH(MaKH);
c.setHoTenKH(request.getParameter("HoTenKH"));
c.setSoDienThoai(request.getParameter("SoDienThoai"));
c.setDiaChiKH(request.getParameter("DiaChiKH"));
c.setNgaySinh(request.getParameter("NgaySinh"));
c.setGioiTinh(request.getParameter("GioiTinh"));

CustomerManager.updateOneCustomer(c);

MyTool.sendRedirect(response, "list_customer.jsp");
%>