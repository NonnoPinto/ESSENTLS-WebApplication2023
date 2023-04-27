<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
<%-- @ include file="/html/cdn.html"--%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Email Confirmation</title>
<!--  JAVASCRIPT AND STYLE  -->
<!--<script src="${pageContext.request.contextPath}/js/login.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />-->
</head>
<body>
    <div class="title">
        <h1>Email Confirmation</h1>
        <hr>
    </div>
    <div class="navbar"><%@include file="navbar.jsp"%></div>
    <c:choose>
        <c:when test="${message.isError()}">
            <div>
                <div role="alert">
                        <span>
                            <c:out value="${message.message}"/>
                        </span>
                        <p>
                            Home: <a href="<c:url value="/home"/>" >Home</a>
                        </p>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <span>
                <c:out value="${message.message}"/>
            </span>  
            <p>
                Login: <a href="<c:url value="/login"/>" >Login</a>
            </p>
        </c:otherwise>
    </c:choose>

	<footer class="footer"><%-- @ include file="/html/footer.html" --%></footer>
</body>
</html>