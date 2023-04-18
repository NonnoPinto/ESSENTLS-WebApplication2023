<!-- <@page import="java.sql.SQLException">
<@page import="java.io.IOException">
<@page import="java.io.PrintWriter">
<@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="true"> -->
<!DOCTYPE html>
<html>
<head>
<!-- <@include file="cdn.html" > -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<!-- <jsp:include page="/navbar.jsp"/> TODO: still on doing-->
<div class="container">
<div class="page-header">
    <h3>An error occurred within the application</h3>
</div>
<!-- <
    if(exception instanceof ClassNotFoundException){
> -->
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3>General error<!-- ClassNotFoundException --></h3>
    </div>
    <div class="panel-body">
        <p>OPS... Something went wrong!<!-- exception.getMessage() --></p>
        <p>
        <input type="button" class="btn btn-danger"
        value="Back" onclick="window.history.back()">
        </p>
    </div>
</div>
<!--
    } else if(exception instanceof IOException){
%>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3>IOException</h3>
    </div>
    <div class="panel-body">
        <p><%= exception.getMessage() %></p>
        <p>
        <input type="button" class="btn btn-danger"
        value="Back" onclick="window.history.back()">
        </p>
    </div>
</div>
    } else if(exception instanceof SQLException){
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3>SQLException</h3>
    </div>
    <div class="panel-body">
        <p><%= exception.getMessage() %></p>
        <p><% exception.printStackTrace(new PrintWriter(out)); %></p>
        <p>
        <input type="button" class="btn btn-danger"
        value="Back" onclick="window.history.back()">
        </p>
    </div>
</div>
    } else {
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3><%= exception.getClass().getName() %></h3>
    </div>
    <div class="panel-body">
        <p><%= exception.getMessage() %></p>
        <p><% exception.printStackTrace(new PrintWriter(out)); %></p>
        <p>
        <input type="button" class="btn btn-default"
        value="Back" onclick="window.history.back()">
        </p>
    </div>
</div>
    }
-->
</div>
<footer class="footer">@include file="footer.html"</footer>
</body>
</html>