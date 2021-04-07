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
				<c:choose>
					<c:when test="${userProfileImage == null}">
						<!-- TODO: put placeholder image -->
						<!-- <div class="empty-slot">No profile picture</div> -->
					</c:when>
					<c:otherwise>
						<img src="data:image/png;base64,${userProfileImage}" alt="...">
					</c:otherwise>
				</c:choose>
				<h1>
					<c:out value="${username}"></c:out>
				</h1>
				<c:choose>
					<c:when test="${isLoggedIn}">
						<div class="list-group">
							<button type="button"
								class="list-group-item list-group-item-action"
								data-toggle="modal" data-target="#addProfileImage">Add
								Profile Image</button>

							<div class="modal fade" id="addProfileImage" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Add a Profile Image</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<form id="imageUploadForm"
												action="${pageContext.request.contextPath}/uploadImage"
												method="post" enctype="multipart/form-data">
												<div class="custom-file">
													<input type="file" class="custom-file-input" id="file"
														name="file"> <label class="custom-file-label"
														for="customFile">Choose file</label>
												</div>
											</form>

											<script>
												$(".custom-file-input").on("change",function() {
													var fileName = $(this).val().split("\\").pop();
													$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
												});
											</script>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<input type="submit" class="btn btn-primary"
												form="imageUploadForm" value="Submit" />
										</div>
									</div>
								</div>
							</div>
							<button type="button"
								class="list-group-item list-group-item-action"
								data-toggle="modal" data-target="#deleteProfileImage">Delete
								Profile Image</button>

							<div class="modal fade" id="deleteProfileImage" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">Delete Profile Image</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-footer">
											<form id="removeImageForm"
												action="${pageContext.request.contextPath}/deleteImage"
												method="post"></form>
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<input type="submit" class="btn btn-primary"
												form="removeImageForm" value="Accept" />
										</div>
									</div>
								</div>
							</div>
							<a href="${pageContext.request.contextPath}/deleteUser"
								class="list-group-item list-group-item-action">Delete
								Profile</a>
						</div>
					</c:when>
				</c:choose>
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
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>