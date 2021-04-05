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

<title>Home</title>
</head>

<body>
	<!-- Navigation Bar -->
	<jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>

	<div class="home-body">
		<div class="row">
			<div class="col-sm-7">
				<c:choose>
					<c:when test="${popularStoryList.isEmpty()}">
						<div class="empty-slot">Nothing To Show</div>
					</c:when>
					<c:otherwise>
						<div id="carouselExampleIndicators" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<c:forEach var="i" begin="0"
									end="${popularStoryList.size() - 1}">
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
								href="#carouselExampleIndicators" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next"
								href="#carouselExampleIndicators" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
					</c:otherwise>
				</c:choose>

				<!-- TODO: dynamically fill with data on carousel swap -->
				<div class="media">
					<img class="align-self-start mr-3"
						src="<%=request.getContextPath()%>/resources/img/test.PNG"
						alt="Generic placeholder image">
					<div class="media-body">
						<h5 class="mt-0">Top-aligned media</h5>
						<p>Cras sit amet nibh libero, in gravida nulla. Nulla vel
							metus scelerisque ante sollicitudin. Cras purus odio, vestibulum
							in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
							nisi vulputate fringilla. Donec lacinia congue felis in faucibus.</p>
						<p>Donec sed odio dui. Nullam quis risus eget urna mollis
							ornare vel eu leo. Cum sociis natoque penatibus et magnis dis
							parturient montes, nascetur ridiculus mus.</p>
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
</body>

</html>