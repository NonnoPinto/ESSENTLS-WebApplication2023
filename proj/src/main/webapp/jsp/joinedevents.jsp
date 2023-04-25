<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Event List</title>
    </head>
    <body>
        <div class="navbar"><%@include file="navbar.jsp"%></div>
        <div class="container">
        <c:choose>
            <c:when test="${message.isError()}">
                <ul>
                    <li>message: <c:out value="${message.message}"/></li>
                </ul>
            </c:when>
            <c:otherwise>
                <h1>Joined Events</h1>

                <c:forEach items="${events}" var="event" varStatus="loop">
                    <hr>
                    <li>Event ID: <c:out value="${event.getId()}"/></li>
                    <li>Event Name: <c:out value="${event.getName()}"/></li>
                    <li>Event Location: <c:out value="${event.getLocation()}"/></li>
                    <li>Event Price: <c:out value="${event.getPrice()}"/></li>
                    <li>Event Description: <c:out value="${event.getDescription()}"/></li>
                    <form action="eventdetail">
                        <button name="id" value="${event.getId()}">Details</button>
                    </form>                    
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>