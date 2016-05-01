<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search customer</title>
<%@ include file="head_define.jsp"%>
<script>
.center_div {
	margin:0 auto;
	width:50%;
}
</script>
</head>
<body>
	<%@ include file="nav_bar.jsp"%>
	<%@ include file="page_content_start.jsp"%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<div class="container center_div">
	<div class="well col-md-4 col-md-offset-4">
		<form class="navbar-form centerd" role="search"
			action="search_customers.jsp" method="post">
			<div class="form-group">
				<input class="form-control" placeholder="Search" type="text" name="HoTenKH">
				<button type="submit" class="btn btn-default">Search</button>
			</div>
		</form>
	</div>
	</div>
	<%@ include file="page_content_end.jsp"%>
</body>
</html>