<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ESN</title>
        <%@ include file="../html/favicon.html"%>
        <%@ include file="../jsp/cdn.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container">
            <h1 class="page-title text-center p-2">Welcome to ESN!</h1>
            <div class="row justify-content-center my-4">
                <div class="col-md-6">
                    <div class="card text-center border-orange">
                        <h2 class="card-title bg-orange color-white p-4">Please login to continue.</h2>
                        <div class="card-body">
                            <a href="<c:url value='/login'/>"><button class="button bg-orange text-white border-orange px-4 py-2">Log in</button></a>
                            <p class="card-text p-2 text-secondary">Don't have an account?</p>
                            <a href="<c:url value='/signup'/>"><button class="button bg-cyan text-white border-cyan px-4 py-2">Sign up!</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>