<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ page contentType="text/html" pageEncoding="UTF-8"   %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta charset="utf-8">
    <title>Error 404 - Not Found</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../html/cdn.html"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="headerup"></div>
<div class="alertTitle">
    <h1>Error 404 - Page not found</h1>
    <hr>
</div>
<div class="container">
    <div class="m-4">
        <div class="alert alert-danger d-flex align-items-center text-center" role="alert">
            <div>
                <p><i class="bi bi-exclamation-triangle-fill color-magenta"></i>Sorry, something went wrong. <i class="bi bi-emoji-frown-fill"></i></p>
            </div>
        </div>
    </div>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>