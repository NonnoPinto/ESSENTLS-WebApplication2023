<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register result</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../html/cdn.html"%>
</head>

<body>
<%@include file="navbar.jsp"%>

<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10">
            <div class="card text-center border-magenta">
                <h1 class="card-title bg-magenta color-white p-4">Register Result</h1>
                <div class="card-body">
                    <div class="my-2 mx-4">
                        <div class="pb-2">
                            <c:choose>
                                <c:when test="${user==null}">
                                    <h2>Something went wrong</h2>
                                    <div class="my-4">
                                        <p class="pb-2 border-bottom border-gray"></p>
                                        <c:out value="${message}"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <h2>Successfully registered</h2>
                                    <div class="my-4">
                                        <p class="pb-2 border-bottom border-gray">Please check your email and click the invitation link to verify this account and login to our website to complete the membership registration.</p>
                                    </div>
                                    <c:out value="Email: ${user.getEmail()}"/>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/html/footer.html"%>

</body>
</html>