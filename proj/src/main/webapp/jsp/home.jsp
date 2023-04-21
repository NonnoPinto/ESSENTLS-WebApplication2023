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
                <h1>All the Events</h1>

                <form action="">
                    <label for="tag">Filter:</label>
                    <select name="tag">
                        <c:forEach items="${tags}" var="tag" varStatus="loop">
                            <option value="${tag.getName()}"><c:out value="${tag.getName()}"/></li>
                        </c:forEach>
                    </select>
                    <br><br>
                    <input type="submit" value="Submit">
                </form>

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
    </body>
</html>