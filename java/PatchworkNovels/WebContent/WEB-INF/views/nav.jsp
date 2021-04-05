<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String login_username = (String)request.getSession().getAttribute("login_username"); %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="home">Patchwork Novels</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link" href="list">Browse
					Library</a></li>
			<c:choose>
				<c:when test="${login_username != null}">
					<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Make New</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="createStory">Story</a>
							<a class="dropdown-item" href="createSnippet">Snippet</a>
						</div>
					</li>
				</c:when>
			</c:choose>
		</ul>
		<ul class="navbar-nav justify-content-end">

			<c:choose>
				<c:when test="${login_username == null}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Login/Sign Up</a>
						<div class="dropdown-menu dropdown-menu-right">
							<form class="px-4 py-3" action="login" method="post">
								<div class="form-group">
									<label for="username">Email address</label> <input
										class="form-control" id="username" name="username"
										placeholder="Username">
								</div>
								<div class="form-group">
									<label for="password">Password</label> <input type="password"
										class="form-control" id="password" name="password"
										placeholder="Password">
								</div>
								<button type="submit" class="btn btn-primary">Sign in</button>
							</form>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="signup">New around here? Sign up</a>
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"><c:out value="${login_username}"></c:out></a>
						<div class="dropdown-menu dropdown-menu-right">
						<!-- TODO: profile image -->
						<img class="align-self-start mr-3"
							src="<%=request.getContextPath()%>/resources/img/test.PNG"
							alt="Generic placeholder image">
							<a class="btn btn-primary" href="logout" role="button">Logout</a>
						</div>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>