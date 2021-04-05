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

<title>Profile</title>
</head>

<body>
	<!-- Navigation Bar -->
	<jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>

	<div class="profile-body">
		<div class="row">
			<div class="col-sm-3">
				<img src="..." class="rounded mx-auto d-block" alt="...">
				<h1></h1>
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action">Add Profile Image</a>
					<a href="#" class="list-group-item list-group-item-action">Remove Profile Image</a>
					<a href="${pageContext.request.contextPath}/changePassword" class="list-group-item list-group-item-action">Change Password</a>
					<a href="${pageContext.request.contextPath}/deleteUser" class="list-group-item list-group-item-action">Delete Profile</a>
				</div>
			</div>
			<div class="col-sm-3">
				<c:choose>
					<c:when test="${userPublishedStories.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div class="list-group">
							<c:forEach var="i" begin="0"
								end="${userPublishedStories.size() - 1}">
								<a
									href="${pageContext.request.contextPath}/story/${userPublishedStories.get(i).getStoryTitle()}"
									class="list-group-item list-group-item-action flex-column align-items-start">
									<div class="d-flex w-100 justify-content-between">
										<h5 class="mb-1">
											<c:out value="${userPublishedStories.get(i).getStoryTitle()}"></c:out>
										</h5>
										<small><c:out
												value="${userPublishedStories.get(i).getStoryTimePosted()}"></c:out></small>
									</div>
									<p class="mb-1">Nothing here yet</p>
								</a>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-3">
				<c:choose>
					<c:when test="${userPublishedSnippets.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div class="list-group">
							<c:forEach var="i" begin="0"
								end="${userPublishedSnippets.size() - 1}">
								<a
									href="${pageContext.request.contextPath}/snippet/${userPublishedSnippets.get(i).getSnippetId()}"
									class="list-group-item list-group-item-action flex-column align-items-start">
									<div class="d-flex w-100 justify-content-between">
										<h5 class="mb-1">
											<c:out
												value="${userPublishedSnippets.get(i).getSnippetText()}"></c:out>
										</h5>
										<small><c:out
												value="${userPublishedSnippets.get(i).getSnippetTimePosted()}"></c:out></small>
									</div>
									<p class="mb-1">Nothing here yet</p>
								</a>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-3">
				<c:choose>
					<c:when test="${userFavoriteStories.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div class="list-group">
							<c:forEach var="i" begin="0"
								end="${userFavoriteStories.size() - 1}">
								<a
									href="${pageContext.request.contextPath}/story/${userFavoriteStories.get(i).getSnippetId()}"
									class="list-group-item list-group-item-action flex-column align-items-start">
									<div class="d-flex w-100 justify-content-between">
										<h5 class="mb-1">
											<c:out value="${userFavoriteStories.get(i).getSnippetText()}"></c:out>
										</h5>
										<small><c:out
												value="${userFavoriteStories.get(i).getSnippetTimePosted()}"></c:out></small>
									</div>
									<p class="mb-1">Nothing here yet</p>
								</a>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>

</html>