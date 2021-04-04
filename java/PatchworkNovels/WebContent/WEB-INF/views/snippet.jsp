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

    <title>Snippets</title>
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

    <div class="snippets-body">
        <div class="row">
            <div class="col-sm-8">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <!-- TODO: fill with popular stories using json to grab most popular (on load?)-->
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="placeholder1"></div>
                        </div>
                        <div class="carousel-item">
                            <div class="placeholder2"></div>
                        </div>
                        <div class="carousel-item">
                            <div class="placeholder3"></div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

                <div class="media">
                    <div class="media-body">
                        <h5 class="mt-0">Top-aligned media</h5>
                        <p>Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin.
                            Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc
                            ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.</p>
                    </div>
                </div>

                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="media">
                                <div class="media-body">
                                    <h5 class="mt-0">Top-aligned media</h5>
                                    <p>Donec sed odio dui. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis
                                        natoque
                                        penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="media">
                                <div class="media-body">
                                    <h5 class="mt-0">Top-aligned media</h5>
                                    <p>Donec sed odio dui. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis
                                        natoque
                                        penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="#" class="list-group-item list-group-item-action">
                            <div class="media">
                                <div class="media-body">
                                    <h5 class="mt-0">Top-aligned media</h5>
                                    <p>Donec sed odio dui. Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis
                                        natoque
                                        penatibus et magnis dis parturient montes, nascetur ridiculus mus.</p>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>

            </div>

            <div class="col-sm-4">
                <h3>Snippet Composer</h3>
                <img src="..." class="rounded mx-auto d-block" alt="...">
            </div>

        </div>
    </div>

</body>

</html>