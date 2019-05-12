<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html>

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>Ghaiath</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/welcome">Lernia</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/welcome">Home</a></li>
				<li><a href="/admin/show-all-users">All Users</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/register"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<div class="container text-center">
		<h3>New Registration</h3>
		<hr>

		<c:if test="${isExist == true}">
			<span class="alert alert-danger" role="alert">${messages.AlreadyExist}</span>
		</c:if>


		<br> <br>
		<form class="form-horizontal" method="POST" action="save-user">
			<input type="hidden" name="id" value="${user.user_id}" />

			<div class="form-group">
				<label class="control-label col-md-3">Personal Number</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="personalNumber"
						title="Please enter exactly 12 digits"
						pattern="^(19|20)?[0-9]{6}[- ]?[0-9]{4}$"
						value="${user.personalNumber}" required maxlength="12" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Username</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="userName"
						value="${user.userName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">First Name</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="firstName"
						value="${user.firstName}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Last Name</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="lastName"
						value="${user.lastName}" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">Address</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="address"
						value="${user.address}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Email</label>
				<div class="col-md-7">
					<input type="email" class="form-control" name="email"
						value="${user.email}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Phone Number</label>
				<div class="col-md-7">
					<input type="text" class="form-control" name="phone"
						value="${user.phone}" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-md-3">Password</label>
				<div class="col-md-7">
					<input type="password" class="form-control" name="password"
						value="${user.password}" />
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Register" />
			</div>
		</form>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			window.setTimeout(function() {
				$(".alert").fadeTo(1000, 0).slideUp(1000, function() {
					$(this).remove();
				});
			}, 5000);

		});
	</script>


</body>
</html>