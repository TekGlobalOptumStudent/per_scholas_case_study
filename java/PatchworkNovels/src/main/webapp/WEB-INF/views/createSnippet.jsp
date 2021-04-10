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

<title>Create</title>
</head>

<body>
	<%
		if(session.getAttribute("login_username") == null){
			request.setAttribute("signup_message", "Please create a new account or login to view this page.");
			response.sendRedirect("/signup");
		}
	%>
	<!-- Navigation Bar -->
	<jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>
	<%@include file="header.jsp"%>
	<div class="create-body">
		<form name="snippetUploadForm" id="snippetUploadForm" action="<%=request.getContextPath()%>/uploadSnippet" method="post">
			<c:choose>
				<c:when test="${snippetText != null}">
					<textarea name="snippetText" id="snippetText" row="200" col="10">
						<c:out value="${snippetText}"></c:out>
					</textarea>
					<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}" />
				</c:when>
				<c:otherwise>
					<textarea name="snippetText" id="snippetText" row="200" col="10"></textarea>
				</c:otherwise>
			</c:choose>
			<input type="hidden" id="snippetAuthor" name="snippetAuthor" value="${login_username}"/>
			<input type="submit" class="btn btn-primary" form="snippetUploadForm" value="Submit" />
		</form>
		<c:out value="${snippet_message}"></c:out>
	</div>
	<%@include file="footer.jsp"%>
	<% session.setAttribute("snippet_message", null); %>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>