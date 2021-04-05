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
				<!-- TODO: change name to composer, populate list with contributors -->
				<h1>Story Composer</h1>
				<img src="..." class="rounded mx-auto d-block" alt="...">
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
							<li class="list-group-item">
								<div class="media">
									<img class="align-self-start mr-3"
										src="<%=request.getContextPath()%>/resources/img/test.PNG"
										alt="Generic placeholder image">
									<div class="media-body">
										<h5 class="mt-0">Top-aligned media</h5>
										<p>Cras sit amet nibh libero, in gravida nulla. Nulla vel
											metus scelerisque ante sollicitudin. Cras purus odio,
											vestibulum in vulputate at, tempus viverra turpis. Fusce
											condimentum nunc ac nisi vulputate fringilla. Donec lacinia
											congue felis in faucibus.</p>
										<p>Donec sed odio dui. Nullam quis risus eget urna mollis
											ornare vel eu leo. Cum sociis natoque penatibus et magnis
											dis parturient montes, nascetur ridiculus mus.</p>
									</div>
								</div>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

	</div>
</body>

</html>