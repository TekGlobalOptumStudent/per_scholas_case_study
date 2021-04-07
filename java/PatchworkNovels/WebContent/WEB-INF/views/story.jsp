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

<title>Stories</title>
</head>

<body>
	<!-- Navigation Bar -->
	<jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>

	<div class="stories-body">
		<div class="row" style="height: 75%">
			<div class="col-sm-8">
				<h1>
					<c:out value="${storyTitle}"></c:out>
				</h1>
				<c:out value="${storyRating}"></c:out>
				<c:choose>
					<c:when test="${storyText.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<ul class="list-group list-group-flush">
							<c:forEach var="i" begin="0" end="${storyText.size() - 1}">
								<li class="list-group-item"><c:out
										value="${storyText.get(i).getSnippetText()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-4">
				<h1>Story Composer</h1>
				<img src="data:image/png;base64,${storyAuthor.getProfileImage()}" class="rounded mx-auto d-block" alt="Profile Image">
				<!-- TODO: populate list with contributors -->
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Cras justo odio</li>
					<li class="list-group-item">Dapibus ac facilisis in</li>
					<li class="list-group-item">Morbi leo risus</li>
					<li class="list-group-item">Porta ac consectetur ac</li>
					<li class="list-group-item">Vestibulum at eros</li>
				</ul>
			</div>
		</div>
		<div class="row" style="height: 25%">
			<div class="col-sm-8">
				<c:choose>
					<c:when test="${storyComments.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<ul class="list-group">
							<c:forEach var="i" begin="0" end="${storyComments.size() - 1}">
								<li class="list-group-item">
									<div class="media">
										<img class="align-self-start mr-3"
											src="data:image/png;base64,${storyComments.get(i).getCommentAuthor().getProfileImage()}"
											alt="Generic placeholder image">
										<div class="media-body">
											<h5 class="mt-0">
												<c:out value="${storyComments.get(i).getCommentAuthor().getUsername()}"></c:out>
											</h5>
											<small>
												<c:out value="${storyComments.get(i).getCommentTimePosted()}"></c:out>
											</small>
											<p>
												<c:out value="${storyComments.get(i).getCommentText()}"></c:out>
											</p>
										</div>
										<small>
											<c:out value="${storyComments.get(i).getCommentRating()}"></c:out>
										</small>
										<c:choose>
											<c:when test="${login_username != null}">
												<form action="${pageContext.request.contextPath}/likeStoryComment" id="likeCommentForm${storyComments.get(i).getCommentId()}">
													<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
													<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
													<input type="submit" class="btn btn-primary"
														form="likeCommentForm${storyComments.get(i).getCommentId()}" value="Like" />
												</form>
												<form action="${pageContext.request.contextPath}/dislikeStoryComment" id="dislikeCommentForm${storyComments.get(i).getCommentId()}">
													<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
													<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
													<input type="submit" class="btn btn-primary"
														form="dislikeCommentForm${storyComments.get(i).getCommentId()}" value="Dislike" />
												</form>
											</c:when>
										</c:choose>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-4">
				<c:choose>
					<c:when test="${login_username != null}">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#addComment">Add Comment</button>
		
						<div class="modal fade" id="addComment" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalCenterTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">Add Comment</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form id="commentForm" action="${pageContext.request.contextPath}/addCommentToStory" method="post">
											<div class="form-group">
		            							<label for="comment" class="col-form-label">Comment:</label>
		            							<textarea class="form-control" id="commentText" name="commentText"></textarea>
		            							<input type="hidden" id="commentAuthor" name="commentAuthor" value="${login_username}">
		            							<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
		          							</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<input type="submit" class="btn btn-primary"
											form="commentForm" value="Accept" />
									</div>
								</div>
							</div>
						</div>				
						<form action="${pageContext.request.contextPath}/likeStory" id="likeStoryForm">
							<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
							<input type="hidden" id="username" name="username" value="${login_username}">
							<input type="submit" class="btn btn-primary"
								form="likeStoryForm" value="Like" />
						</form>
						<form action="${pageContext.request.contextPath}/dislikeStory" id="dislikeStoryForm">
							<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
							<input type="hidden" id="username" name="username" value="${login_username}">
							<input type="submit" class="btn btn-primary"
								form="dislikeStoryForm" value="Dislike" />
						</form>
					</c:when>
				</c:choose>
			</div>
		</div>

	</div>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>