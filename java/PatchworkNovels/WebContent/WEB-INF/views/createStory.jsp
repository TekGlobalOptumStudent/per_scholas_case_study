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

    <title>Create</title>
</head>

<body>
    <!-- Navigation Bar -->
    <jsp:include page="nav.jsp">
		<jsp:param name="user" value="" />
	</jsp:include>

    <div class="create-body">
        <div class="row" style="height: 10%">
            <div class="placeholder1"></div>
        </div>
        <div class="row" style="height: 80%">
            <!-- TODO: create interactive ui with React -->
            <div class="text-container">
                <div class="placeholder4"></div>
            </div>
            <div class="snippet-list">
            </div>
        </div>
        <div class="row" style="height: 10%">
            <div class="placeholder1"></div>
        </div>
    </div>

</body>

</html>