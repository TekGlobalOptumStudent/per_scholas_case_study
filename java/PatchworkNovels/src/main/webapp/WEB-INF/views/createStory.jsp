<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<%@include file="header.jsp"%>
	<div class="create-body">
		<div class="row" style="height: 10%">
			<form name="storyUploadForm" id="storyUploadForm" action="<%=request.getContextPath()%>/uploadStory" method="post">
		        <input type="hidden" id="storyText" name="storyText" value="">
		        <input type="hidden" id="storyAuthor" name="storyAuthor" value="${login_username}">
		        <c:choose>
		        	<c:when test="${storySnippets != null}">
		        		<h1><c:out value="${storyTitle}"></c:out></h1>
		        		<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
		        	</c:when>
		        	<c:otherwise>
		        		<h4></>Title Your Story</h4>
		        		<input type="text" id="storyTitle" name="storyTitle">
		        	</c:otherwise>
		        </c:choose>
			</form>
			<p class="error"><c:out value="${story_message}"></c:out></p>
		</div>
		<div class="row" style="height: 80%">
			<div class="col">
				<ul class="list-group dropzone" id="storyTextDropzone" name="storyTextDropzone">
					<c:choose>
						<c:when test="${storySnippets != null && !storySnippets.isEmpty()}">
							<c:forEach var="i" begin="0" end="${storySnippets.size() - 1}">
								<li class="list-group-item draggable" draggable="true" id="${storySnippets.get(i).getSnippetId()}">
									<c:out value="${storySnippets.get(i).getSnippetText()}"></c:out>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<div class="col">
				<ul class="list-group dropzone">
					<c:choose>
						<c:when test="${allSnippets!= null && !allSnippets.isEmpty()}">
							<c:forEach var="i" begin="0" end="${allSnippets.size() - 1}">
								<li class="list-group-item draggable" draggable="true" id="${allSnippets.get(i).getSnippetId()}">
									<c:out value="${allSnippets.get(i).getSnippetText()}"></c:out>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="row" style="height: 10%">
			<input type="submit" class="btn btn-primary" form="storyUploadForm" value="Submit" onclick="captureStoryText();"/>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<% session.setAttribute("story_message", null); %>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>