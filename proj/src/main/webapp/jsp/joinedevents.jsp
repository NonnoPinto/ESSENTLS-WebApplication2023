<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Event List</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="title">
            <h1>Joined Events</h1>
            <hr>
        </div>
        <div class="container">
        <c:choose>
            <c:when test="${message.isError()}">
                <ul>
                    <li>message: <c:out value="${message.message}"/></li>
                </ul>
            </c:when>
            <c:otherwise>
                <c:forEach items="${events}" var="event" varStatus="loop">
                    <hr>
                    <li>Event ID: <c:out value="${event.id}"/></li>
                    <li>Event Name: <c:out value="${event.name}"/></li>
                    <li>Event Location: <c:out value="${event.location}"/></li>
                    <li>Event Price: <c:out value="${event.price}"/></li>
                    <li>Event Description: <c:out value="${event.description}"/></li>
                    <form action="eventdetail">
                        <button name="id" value="${event.id}">Details</button>
                    </form>
                    <a href="confirmEvent?id=${event.id}"><button>Additional info</button></a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>