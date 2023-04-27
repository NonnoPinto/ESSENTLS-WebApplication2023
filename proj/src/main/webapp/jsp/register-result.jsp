<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 17/04/2023
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport">
    <title>User Edit</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="title">
    <h1>User Edit</h1>
    <hr>
</div>
<c:choose>
    <c:when test="${user==null}">
        <h2>Something went wrong.</h2>
        <c:out value="${message}"/>
    </c:when>
    <c:otherwise>
        <c:out value="Successfully registered."/>
        <br/>
        <p>Please check your email and click the invitation link to verify this account and login to our website to complete the membership registration.</p>
        <c:out value="Email: ${user.getEmail()}"/>
        <br/>
        
    </c:otherwise>
</c:choose>

<footer class="footer"><%@include file="/html/footer.html"%></footer>

</body>
</html>