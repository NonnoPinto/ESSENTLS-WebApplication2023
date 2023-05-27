<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Email Confirmation</title>
    <%@ include file="../html/favicon.html"%>
</head>

<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <h1 class="page-title text-center p-2">Email Confirmation</h1>
    <div class="row justify-content-center my-4">
        <div class="col-md-6 text-center">
            <c:choose>
                <c:when test="${message.isError()}">
                    <div>
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${message.message}"/>
                        </div>
                        <p>
                            Home: <a href="<c:url value="/home"/>" >Home</a>
                        </p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <div class="alert alert-success" role="alert">
                            <c:out value="${message.message}"/>
                        </div>
                        <p>
                            Login: <a href="<c:url value="/login"/>" >Login</a>
                        </p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>