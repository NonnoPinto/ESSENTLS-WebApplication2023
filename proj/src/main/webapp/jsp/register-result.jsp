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


<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Edit</title>
</head>

<body>
<%@include file="navbar.jsp"%>
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

<%@include file="/html/footer.html"%>

</body>
</html>