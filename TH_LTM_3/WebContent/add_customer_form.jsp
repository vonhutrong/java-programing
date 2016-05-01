<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>add customer</title>
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
			<form action="add_customer.jsp" method="post">
				<label><h3>Add customer</h3></label>
				<div class="form-group">
					<label>Name</label>
					<input class="form-control" type="text" placeholder="ex: John" name="HoTenKH">
				</div>
				<div class="form-group">
					<label>Phone number</label>
					<input class="form-control" type="text" placeholder="ex: 0123456789" name="SoDienThoai">
				</div>
				<div class="form-group">
					<label>Address</label>
					<input class="form-control" type="text" placeholder="ex: California" name="DiaChiKH">
				</div>
				<div class="form-group">
					<label>Date of birth</label>
					<input class="form-control" type="text" placeholder="ex: 31-1-2012" name="NgaySinh">
				</div>
				<div class="form-group">
					<label>Sex</label>
					<label class="radio-inline">
						<input type="radio" name="GioiTinh" value="Male">
						Male
					</label>
					<label class="radio-inline">
						<input type="radio" name="GioiTinh" value="Female">
						Female
					</label>
				</div>
				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
	</div>
	<%@ include file="page_content_end.jsp"%>
</body>
</html>