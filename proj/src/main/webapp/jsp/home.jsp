<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
    </head>
    <body>

        <c:choose>
            <c:when test="${message.isError()}">
                <ul>
                    <li>message: <c:out value="${message.message}"/></li>
                </ul>
            </c:when>
            <c:otherwise>
                <c:forEach items="${events}" var="event" varStatus="loop">
                    <li><c:out value="${event.getName()}"/></li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>