<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Thử request parameters (Form) với Tiếng Việt Unicode</title>
</head>

<body>

<%
request.setCharacterEncoding("UTF-8");
String title = "JSP với Tiếng Việt";
String name = request.getParameter("name");
%>

request encoding: <%= request.getCharacterEncoding() %><br />
response encoding: <%= response.getCharacterEncoding() %><br /><br />

Title: <%= title %><br />
Tên: <%= name %><br /><br />

<form method="GET" action="lang.jsp">
Tên: <input type="text" name="name" value="" >
<input type="submit" name="submit" value="GET Submit" />
</form>

<form method="POST" action="lang.jsp">
Tên: <input type="text" name="name" value="" >
<input type="submit" name="submit" value="POST Submit" />
</form>

</body>
</html>