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
	<nav class="navbar navbar-light" style="background-color: #d5d2f4;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/admin/admin-dashboard">Admin
					Dashboard</a>
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

	<div class="container text-center" id="tasksDiv">
		<h3>All Users</h3>
		<hr>
		<div class="table-responsive">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>UserName</th>
						<th>Personal Number</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Address</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Rating</th>
						<th>Active</th>
						<th>Delete</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users}">
						<tr>
							<td>${user.user_id}</td>
							<td>${user.userName}</td>
							<td>${user.personalNumber}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.address}</td>
							<td>${user.email}</td>
							<td>${user.phone}</td>
							<td>${user.rating}</td>
							<td>${user.active}</td>

							<td><a href="/admin/delete-user?id=${user.user_id}"><span
									class="glyphicon glyphicon-trash"></span></a></td>
							<td><a href="/admin/edit-user?id=${user.user_id}"><span
									class="glyphicon glyphicon-pencil"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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