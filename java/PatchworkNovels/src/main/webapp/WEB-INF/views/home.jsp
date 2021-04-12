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

<title>Home</title>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="home-body">
		<div class="row">
			<div class="col-sm-7">
				<c:choose>
					<c:when test="${popularStoryList.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div id="popularStories" class="carousel slide" data-ride="carousel" style="height: 50%;">
							<div class="carousel-inner">
								<c:forEach var="i" begin="0" end="${popularStoryList.size() - 1}">
									<c:choose>
										<c:when test="${i==0}">
											<div class="carousel-item active">
												<div class="placeholder1">
													<div class="d-flex w-100 justify-content-between">
														<h5 class="mb-1">
															<c:out value="${popularStoryList.get(i).getStoryTitle()}"></c:out>
														</h5>
														<small><c:out
																value="${popularStoryList.get(i).getStoryTimePosted()}"></c:out></small>
													</div>
													<p class="mb-1">
														<c:out
															value="${popularStoryList.get(i).getStoryAuthor().getUsername()}"></c:out>
													</p>
													<small><c:out
															value="${popularStoryList.get(i).getStoryRating()}"></c:out></small>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="carousel-item">
												<div class="placeholder1">
													<div class="d-flex w-100 justify-content-between">
														<h5 class="mb-1">
															<c:out value="${popularStoryList.get(i).getStoryTitle()}"></c:out>
														</h5>
														<small><c:out
																value="${popularStoryList.get(i).getStoryTimePosted()}"></c:out></small>
													</div>
													<p class="mb-1">
														<c:out
															value="${popularStoryList.get(i).getStoryAuthor().getUsername()}"></c:out>
													</p>
													<small><c:out
															value="${popularStoryList.get(i).getStoryRating()}"></c:out></small>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
							<a class="carousel-control-prev"
								href="#popularStories" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next"
								href="#popularStories" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
					</c:otherwise>
				</c:choose>

				<div id="popularStoryComments" class="carousel slide" data-ride="carousel" style="height: 50%;">
					<div class="carousel-inner">
						<c:forEach var="i" begin="0" end="${popularStoryList.size() - 1}">
							<c:choose>
								<c:when test="${i==0}">
									<div class="carousel-item active">
										<c:choose>
											<c:when test="${popularStoryList.get(i).getStoryComments().isEmpty()}">
												<div class="empty-slot">Nothing to show</div>
											</c:when>
											<c:otherwise>
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
											</c:otherwise>
										</c:choose>
									</div>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<c:choose>
											<c:when test="${popularStoryList.get(i).getStoryComments().isEmpty()}">
												<div class="empty-slot">Nothing to show</div>
											</c:when>
											<c:otherwise>
												<a href="<%=request.getContextPath()%>/profile/${popularStoryList.get(i).getStoryComments().get(0).getCommentAuthor().getUsername()}">
													<div class="comment-img mr-3">
					                                    <c:choose>
					                                        <c:when test="${popularStoryList.get(i).getStoryComments().get(0).getCommentAuthor().getProfileImage() == null}">
					                                            <img class="align-self-start" src="../../resources/img/blank.png" alt="Profile Image">
					                                        </c:when>
					                                        <c:otherwise>
					                                            <img class="align-self-start"
																	src="data:image/png;base64,${popularStoryList.get(i).getStoryComments().get(0).getCommentAuthor().getProfileImage()}"
																	alt="Profile Image">
					                                        </c:otherwise>
					                                    </c:choose>
													</div>
												</a>
												<div class="media-body">
													<h5 class="mt-0">
														<c:out value="${popularStoryList.get(i).getStoryComments().get(0).getCommentAuthor().getUsername()}"></c:out>
													</h5>
													:
													<small>
														<fmt:formatDate value="${popularStoryList.get(i).getStoryComments().get(0).getCommentTimePosted()}" type="date" pattern="MMM-dd-yyyy hh:mm:ss"/>
													</small>
													<p>
														<c:out value="${popularStoryList.get(i).getStoryComments().get(0).getCommentText()}"></c:out>
													</p>
												</div>
												<small>
													<c:out value="${popularStoryList.get(i).getStoryComments().get(0).getCommentRating()}"></c:out>
												</small>
											</c:otherwise>
										</c:choose>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>				
				
				

			</div>
			<div class="col-sm-5">
				<c:choose>
					<c:when test="${recentStoryList.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div class="list-group">
							<c:forEach var="i" begin="0" end="${recentStoryList.size() - 1}">
								<a
									href="${pageContext.request.contextPath}/story/${recentStoryList.get(i).getStoryTitle()}"
									class="list-group-item list-group-item-action flex-column align-items-start">
									<div class="d-flex w-100 justify-content-between">
										<h5 class="mb-1">
											<c:out value="${recentStoryList.get(i).getStoryTitle()}"></c:out>
										</h5>
										<small><c:out
												value="${recentStoryList.get(i).getStoryTimePosted()}"></c:out></small>
									</div> <small><c:out
											value="${recentStoryList.get(i).getStoryAuthor().getUsername()}"></c:out></small>
									<p class="mb-1">Nothing here yet</p>
								</a>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
<!-- External JS -->
<script src="<%=request.getContextPath()%>/resources/scripts/script.js" /></script>
</html>