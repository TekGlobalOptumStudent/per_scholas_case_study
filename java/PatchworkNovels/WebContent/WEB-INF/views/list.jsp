<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

    <!-- External CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/styles/global.css"/>

    <title>List</title>
</head>

<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="home">Patchwork Novels</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="list">Browse Library</a>
                </li>
                <!-- TODO: hide if not logged in -->
                <li class="nav-item">
                    <a class="nav-link" href="create">Make New</a>
                </li>
            </ul>
            <ul class="navbar-nav justify-content-end">
                <!-- TODO: dynamically change to profile preview if logged in -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Login/Sign Up
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <form class="px-4 py-3">
                            <div class="form-group">
                                <label for="exampleDropdownFormEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleDropdownFormEmail1"
                                    placeholder="email@example.com">
                            </div>
                            <div class="form-group">
                                <label for="exampleDropdownFormPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                    placeholder="Password">
                            </div>
                            <button type="submit" class="btn btn-primary">Sign in</button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="signup">New around here? Sign up</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <div class="list-body">
        <div class="row">
            <div class="col">
                <h1>Snippets</h1>
                <div class="list-group">
                	<c:forEach var="i" begin="0" end="${allSnippets.size() - 1}">
						<a href="#"
							class="list-group-item list-group-item-action flex-column align-items-start">
							<div class="d-flex w-100 justify-content-between">
								<small><c:out value="${allSnippets.get(i).getSnippetTimePosted()}"></c:out></small>
							</div>
							<small><c:out value="${allSnippets.get(i).getSnippetAuthor().getUsername()}"></c:out></small>
							<p class="mb-1">Nothing here yet</p>
						</a> 
					</c:forEach>
                </div>
            </div>
            <div class="col">
                <h1>Stories</h1>
                <div class="list-group">
                    <c:forEach var="i" begin="0" end="${allStories.size() - 1}">
						<a href="#"
							class="list-group-item list-group-item-action flex-column align-items-start">
							<div class="d-flex w-100 justify-content-between">
								<h5 class="mb-1"><c:out value="${allStories.get(i).getStoryTitle()}"></c:out></h5>
								<small><c:out value="${allStories.get(i).getStoryTimePosted()}"></c:out></small>
							</div>
							<small><c:out value="${allStories.get(i).getStoryAuthor().getUsername()}"></c:out></small>
							<p class="mb-1">Nothing here yet</p>
						</a> 
					</c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>

</html>