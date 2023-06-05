<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Email Confirmation</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp"%>
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
                        <div class="m-4">
                            <a href="<c:url value='/login'/>">
                                <button class="button btn same-width-login bg-orange text-white border-orange px-4 py-2 m-1" >Login</button>
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>