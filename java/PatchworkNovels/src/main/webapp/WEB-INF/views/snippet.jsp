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

<title>Snippets</title>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="snippets-body">
		<div class="row" style="height: 75%">
			<div class="col-sm-8">
				<div id="stories" class="carousel slide" data-ride="carousel" style="height: 50%;">
					<div class="carousel-inner">
						<c:choose>
							<c:when test="${snippetStories.isEmpty()}">
								<div class="empty-slot">Nothing To Show</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="i" begin="0" end="${snippetStories.size() - 1}">
									<a href="<%=request.getContextPath()%>/story/${snippetStories.get(i).getStoryTitle()}">
										<div class="story-container">
											<c:choose>
												<c:when test="${i==0}">
													<div class="carousel-item active">
														<div class="media">
															<div class="media-body">
																<h5 class="mt-0">
																	<c:out value="${snippetStories.get(i).getStoryTitle()}"></c:out>
																</h5>
																<small><c:out value="${snippetStories.get(i).getStoryAuthor().getUsername()}"></c:out></small>
																<c:choose>
																	<c:when test="${snippetStories.get(i).getStoryText().isEmpty()}">
																		<div class="empty-slot">Nothing to show</div>
																	</c:when>
																	<c:otherwise>
																		<p><c:out value="${snippetStories.get(i).getStoryText().get(i).getSnippetText()}"></c:out></p>
																	</c:otherwise>
																</c:choose>
																<small><fmt:formatDate value="${snippetStories.get(i).getStoryTimePosted()}" type="date" pattern="MMM-dd-yyyy hh:mm:ss"/></small>
															</div>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="carousel-item">
														<div class="media">
															<div class="media-body">
																<h5 class="mt-0">
																	<c:out value="${snippetStories.get(i).getStoryTitle()}"></c:out>
																</h5>
																<small><c:out value="${snippetStories.get(i).getStoryAuthor().getUsername()}"></c:out></small>
																<c:choose>
																	<c:when test="${snippetStories.get(i).getStoryText().isEmpty()}">
																		<div class="empty-slot">Nothing to show</div>
																	</c:when>
																	<c:otherwise>
																		<p><c:out value="${snippetStories.get(i).getStoryText().get(i)}"></c:out></p>
																	</c:otherwise>
																</c:choose>
																<small><fmt:formatDate value="${snippetStories.get(i).getStoryTimePosted()}" type="date" pattern="MMM-dd-yyyy hh:mm:ss"/></small>
															</div>>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
									</a>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<a class="carousel-control-prev" href="#stories"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#stories"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div> 

				<div class="media">
					<div class="media-body">
						<p>${snippetText}</p>
					</div>
				</div>

			</div>

			<div class="col-sm-4">
				<h3><c:out value="${snippetAuthor.getUsername()}"></c:out></h3>
				<a href="<%=request.getContextPath()%>/profile/${snippetAuthor.getUsername()}">
					<div class="profile-img">
					    <c:choose>
						    <c:when test="${snippetAuthor.getProfileImage() == null}">
						    	<img class="align-self-start" src="../../resources/img/blank.png" alt="Profile Image">
						    </c:when>
						    <c:otherwise>
							    <img class="align-self-start"
									src="data:image/png;base64,${snippetAuthor.getProfileImage()}"
									alt="Profile Image">
						    </c:otherwise>
						</c:choose>
					</div>
				</a>
			</div>
		</div>
		<div class="row" style="height: 25%">
			<div class="col-sm-8">
				<c:choose>
					<c:when test="${snippetComments.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<ul class="list-group">
							<c:forEach var="i" begin="0" end="${snippetComments.size() - 1}">
								<li class="list-group-item">
									<div class="media">
										<a href="<%=request.getContextPath()%>/profile/${storyComments.get(i).getCommentAuthor().getUsername()}">
											<div class="comment-img mr-3">
				                                 <c:choose>
				                                     <c:when test="${storyComments.get(i).getCommentAuthor().getProfileImage() == null}">
				                                         <img class="align-self-start mr-3" src="../../resources/img/blank.png" alt="Profile Image">
				                                     </c:when>
				                                     <c:otherwise>
				                                         <img class="align-self-start mr-3"
															src="data:image/png;base64,${storyComments.get(i).getCommentAuthor().getProfileImage()}"
															alt="Profile Image">
				                                     </c:otherwise>
				                                 </c:choose>
				                             </div>
										</a>
										<div class="media-body">
											<h5 class="mt-0">
												<c:out value="${snippetComments.get(i).getCommentAuthor().getUsername()}"></c:out>
											</h5>
											:
											<small>
												<fmt:formatDate value="${snippetComments.get(i).getCommentTimePosted()}" type="date" pattern="MMM-dd-yyyy hh:mm:ss"/>
											</small>
											<p>
												<c:out value="${snippetComments.get(i).getCommentText()}"></c:out>
											</p>
										</div>
										<small>
											<c:out value="${snippetComments.get(i).getCommentRating()}"></c:out>
										</small>
										<c:choose>
											<c:when test="${login_username != null}">
												<c:choose>
													<c:when test="${login_username.equals(snippetComments.get(i).getCommentAuthor().getUsername())}">
														<button type="button" class="btn btn-primary" data-toggle="modal"
															data-target="#editComment${i}">Edit</button>
											
														<div class="modal fade" id="editComment${i}" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalCenterTitle"
															aria-hidden="true">
															<div class="modal-dialog modal-dialog-centered" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLongTitle">Edit Comment</h5>
																		<button type="button" class="close" data-dismiss="modal"
																			aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body">
																		<form id="editCommentForm${i}" action="${pageContext.request.contextPath}/editSnippetComment" method="post">
																			<label for="comment" class="col-form-label">Comment:</label>
																			<textarea class="form-control" id="commentText" name="commentText">
																				<c:out value="${snippetComments.get(i).getCommentText()}"></c:out>
																			</textarea>
																			<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}">
																			<input type="hidden" id="commentId" name="commentId" value="${snippetComments.get(i).getCommentId()}">
																		</form>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal">Close</button>
																		<input type="submit" class="btn btn-primary"
																			form="editCommentForm${i}" value="Accept" />
																	</div>
																</div>
															</div>
														</div>
														<button type="button" class="btn btn-primary" data-toggle="modal" 
															data-target="#deleteComment${i}">Delete</button>
								
														<div class="modal fade" id="deleteComment${i}" tabindex="-1"
															role="dialog" aria-labelledby="exampleModalCenterTitle"
															aria-hidden="true">
															<div class="modal-dialog modal-dialog-centered" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title">Delete Comment</h5>
																		<button type="button" class="close" data-dismiss="modal"
																			aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-header">
																		<h6>Are you sure?</h6>
																	</div>
																	<div class="modal-footer">
																		<form id="deleteCommentForm${i}" action="${pageContext.request.contextPath}/deleteSnippetComment" method="post">
																			<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}">
																			<input type="hidden" id="commentId" name="commentId" value="${snippetComments.get(i).getCommentId()}">
																		</form>
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal">Cancel</button>
																		<input type="submit" class="btn btn-primary"
																			form="deleteCommentForm${i}" value="Confirm" />
																	</div>
																</div>
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<form action="${pageContext.request.contextPath}/likeSnippetComment" id="likeCommentForm${snippetComments.get(i).getCommentId()}">
															<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}">
															<input type="hidden" id="commentId" name="commentId" value="${snippetComments.get(i).getCommentId()}">
															<input type="submit" class="btn btn-primary"
																form="likeCommentForm${snippetComments.get(i).getCommentId()}" value="Like" />
														</form>
														<form action="${pageContext.request.contextPath}/dislikeSnippetComment" id="dislikeCommentForm${snippetComments.get(i).getCommentId()}">
															<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}">
															<input type="hidden" id="commentId" name="commentId" value="${snippetComments.get(i).getCommentId()}">
															<input type="submit" class="btn btn-primary"
																form="dislikeCommentForm${snippetComments.get(i).getCommentId()}" value="Dislike" />
														</form>
													</c:otherwise>
												</c:choose>
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
										<form id="commentForm" action="${pageContext.request.contextPath}/addCommentToSnippet" method="post">
											<div class="form-group">
		            							<label for="comment" class="col-form-label">Comment:</label>
		            							<textarea class="form-control" id="commentText" name="commentText"></textarea>
		            							<input type="hidden" id="commentAuthor" name="commentAuthor" value="${login_username}">
		            							<input type="hidden" id="snippetId" name="snippetId" value="${snippetId}">
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
						<p><div class="error"><c:out value="${comment_message}"></c:out></div></p>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<% session.setAttribute("comment_message", null); %>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>