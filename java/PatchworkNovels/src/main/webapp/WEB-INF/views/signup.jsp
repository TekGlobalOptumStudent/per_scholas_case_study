<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>

<!-- External CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/styles/global.css" />

<title>Sign Up</title>
</head>

<body>
	<!-- Navigation Bar -->
	<jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>
	<%@include file="header.jsp"%>
	<div class="signup-body">
		<div class="form-outer-container">
			<form action="signup" method="post">
				<div class="form-group">
					<label>Username</label>
					<c:choose>
						<c:when test="${login_username == null}">
							<input type="text" class="form-control" id="username" name="username"
							minlength="4" maxlength="20"
							placeholder="Username">
						</c:when>
						<c:otherwise>
							<h4><c:out value="${login_username}"></c:out></h4>
							<input type="hidden" id="username" name="username" value="${login_username}">
						</c:otherwise>
					</c:choose>
					
				</div>
				<div class="form-group">
					<label>Password</label>
					<input type="password" class="form-control" id="password" name="password"
					minlength="4" maxlength="20"
					placeholder="Password">
				</div>
				<div class="form-group">
					<label>Confirm Password</label>
					<input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
					minlength="4" maxlength="20"
					placeholder="Confirm password">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
			<c:out value="${message}"></c:out>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>