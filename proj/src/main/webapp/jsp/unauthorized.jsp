<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ page contentType="text/html" pageEncoding="UTF-8"   %>
<%-- @ include file="/html/cdn.html" --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta charset="utf-8">
    <title>Welcome</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="headerup"></div>
<div class="title">
    <h1>Login</h1>
    <hr>
</div>
<div class="container">

    <div style="margin: 30px 0">
        <div class="alert alert-danger" role="alert">
            <div>
                <p>You are not authorized.</p>
            </div>
        </div>
    </div>
</div>
<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>