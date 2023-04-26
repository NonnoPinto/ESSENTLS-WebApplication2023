<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport">
        <title>Home</title>
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
                <h1>All the Events</h1>

                <form action="">
                    <label for="tag">Filter:</label>
                    <select name="tag">
                        <c:forEach items="${tags}" var="tag" varStatus="loop">
                            <option value="${tag.getName()}"><c:out value="${tag.getName()}"/></option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <input type="submit" value="Submit">
                </form>

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
                    <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 1}">
                        <a href="eventparticipants?id=${event.id}"><button>(Admin) Participants List</button></a>
                        <a href="editEvent?id=${event.id}"><button>(Admin) Edit</button></a>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>