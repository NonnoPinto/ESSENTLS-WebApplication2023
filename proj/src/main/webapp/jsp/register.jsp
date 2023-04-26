<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport">
    <meta charset="utf-8">
    <title>Register</title>
</head>
<body>
    <div class="navbar"><%@include file="navbar.jsp"%></div>
    <div class="title">
        <h1>Register</h1>
        <hr>
    </div>
     <c:choose>
        <c:when test="${message.error}">
            <p><c:out value="${message.message}"/></p>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
    <form method="POST" action="<c:url value="/register"/>">
        <div>
            <label for="email">Enter your email:</label>
            <input name="email" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="text"/><br/><br/>
            <!-- if we want to block mails with + in the first argument, this can be a trick to register multiple with gmail,.. -->
            <!-- <input name="email" pattern="^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="text"/><br/><br/> -->
            <label for="password">password:</label>
            <input name="password" type="password"/><br/><br/>
            <label for="rpassword">repeat password:</label>
            <input name="rpassword" type="password"/><br/><br/>
        </div>
        <button type="submit">Submit</button><br/>
    </form>
</body>
</html>