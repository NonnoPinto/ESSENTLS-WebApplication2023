<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <title>Register</title>
</head>
<body>
    <%@include file="navbar.jsp"%>
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
    <form method="POST" action="<c:url value="/register"/>" enctype="multipart/form-data">
        <div>
            <label for="email">Enter your email:</label>
            <input name="email" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="email"/><br/><br/>
            <label for="password">password:</label>
            <input name="password" type="password"/><br/><br/>
            <label for="rpassword">repeat password:</label>
            <input name="rpassword" type="password"/><br/><br/>
        </div>
        <button type="submit">Submit</button><br/>
    </form>
    <footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>