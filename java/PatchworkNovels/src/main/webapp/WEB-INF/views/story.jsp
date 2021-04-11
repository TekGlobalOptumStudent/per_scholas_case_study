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

<title>Stories</title>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="stories-body">
		<div class="row" style="height: 75%">
			<div class="col-sm-8">
				<h1><c:out value="${storyTitle}"></c:out></h1>
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
				<h1><c:out value="${storyAuthor.getUsername()}"></c:out></h1>
				<a href="<%=request.getContextPath()%>/profile/${storyAuthor.getUsername()}">
					<div class="profile-img">
					    <c:choose>
						    <c:when test="${storyComments.get(i).getCommentAuthor().getProfileImage() == null}">
						    	<img class="align-self-start" src="../../resources/img/blank.png" alt="Profile Image">
						    </c:when>
						    <c:otherwise>
							    <img class="align-self-start"
									src="data:image/png;base64,${storyComments.get(i).getCommentAuthor().getProfileImage()}"
									alt="Profile Image">
						    </c:otherwise>
					    </c:choose>
					</div>
				</a>
				<p><h3><c:out value="${storyRating}"></c:out> Likes</h3></p>
				<p>
					<div class="error"><c:out value="${comment_message}"></c:out></div>
				</p>
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
									<div class="comment-container">
										<div class="media">
											<a href="<%=request.getContextPath()%>/profile/${storyComments.get(i).getCommentAuthor().getUsername()}">
												<div class="comment-img mr-3">
				                                    <c:choose>
				                                        <c:when test="${storyComments.get(i).getCommentAuthor().getProfileImage() == null}">
				                                            <img class="align-self-start" src="../../resources/img/blank.png" alt="Profile Image">
				                                        </c:when>
				                                        <c:otherwise>
				                                            <img class="align-self-start"
																src="data:image/png;base64,${storyComments.get(i).getCommentAuthor().getProfileImage()}"
																alt="Profile Image">
				                                        </c:otherwise>
				                                    </c:choose>
												</div>
											</a>
											<div class="media-body">
												<h5 class="mt-0">
													<c:out value="${storyComments.get(i).getCommentAuthor().getUsername()}"></c:out>
												</h5>
												:
												<small>
													<fmt:formatDate value="${storyComments.get(i).getCommentTimePosted()}" type="date" pattern="MMM-dd-yyyy hh:mm:ss"/>
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
													<c:choose>
														<c:when test="${login_username.equals(storyComments.get(i).getCommentAuthor().getUsername())}">
															<button type="button" class="btn btn-primary d-flex" data-toggle="modal"
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
																			<form id="editCommentForm${i}" action="${pageContext.request.contextPath}/editStoryComment" method="post">
																				<label for="comment" class="col-form-label">Comment:</label>
																				<textarea class="form-control" id="commentText" name="commentText">
																					<c:out value="${storyComments.get(i).getCommentText()}"></c:out>
																				</textarea>
																				<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
																				<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
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
															<button type="button" class="btn btn-primary d-flex" data-toggle="modal" 
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
																			<form id="deleteCommentForm${i}" action="${pageContext.request.contextPath}/deleteStoryComment" method="post">
																				<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
																				<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
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
															<form action="${pageContext.request.contextPath}/likeStoryComment" id="likeCommentForm${i}">
																<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
																<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
																<input type="submit" class="btn btn-primary d-flex"
																	form="likeCommentForm${i}" value="Like" />
															</form>
															<form action="${pageContext.request.contextPath}/dislikeStoryComment" id="dislikeCommentForm${i}">
																<input type="hidden" id="storyTitle" name="storyTitle" value="${storyTitle}">
																<input type="hidden" id="commentId" name="commentId" value="${storyComments.get(i).getCommentId()}">
																<input type="submit" class="btn btn-primary d-flex"
																	form="dislikeCommentForm${i}" value="Dislike" />
															</form>
														</c:otherwise>
													</c:choose>
												</c:when>
											</c:choose>
										</div>
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
	<%@include file="footer.jsp"%>
	<% session.setAttribute("comment_message", null); %>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>