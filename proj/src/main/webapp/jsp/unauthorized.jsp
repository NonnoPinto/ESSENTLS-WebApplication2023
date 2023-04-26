<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ page contentType="text/html" pageEncoding="UTF-8"   %>
<%-- @ include file="/html/cdn.html" --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome</title>
<!--     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">

    <title>Login</title>
<!--     <script src="${pageContext.request.contextPath}/js/login.js"></script> -->
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
                    You are not authorized.
                </div>
           <div>
                   <!-- <a class="btn btn-danger btn-lg" href="${pageContext.request.contextPath}/jsp/home.jsp" role="button">Back</a> -->
           </div>

       </div>

    </div>

</div>
<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>