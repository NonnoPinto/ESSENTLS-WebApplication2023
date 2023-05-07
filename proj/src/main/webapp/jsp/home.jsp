<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
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
                    <label for="tag">Tag:</label>
                    <select name="tag">
                        <option value="${tag}" selected hidden>${tag}</option>
                        <option value="">---no tag---</option>
                        <c:forEach items="${tags}" var="tag" varStatus="loop">
                            <option value="${tag.getName()}"><c:out value="${tag.getName()}"/></option>
                        </c:forEach>
                    </select>
                    <label for="cause">Cause:</label>
                    <select name="cause">
                        <option value="${cause.getId()}" selected hidden>${cause.getName()}</option>
                        <option value="">---no cause---</option>
                        <c:forEach items="${causes}" var="cause" varStatus="loop">
                            <option value="${cause.getId()}"><c:out value="${cause.getName()}"/></option>
                        </c:forEach>
                    </select>
                    <label for="srch">Search:</label>
                    <input type="search" id="srch" name="srch" placeholder="name,location or description" value="${srch}" style="width: 400px"/>
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
                        <c:forEach items="${isOrganizer}" var="org" varStatus="loop">
                            <c:if test="${org.key==event.id && org.value}">
                                <a href="eventparticipants?id=${event.id}"><button>(Organizer) Participants List</button></a>
                                <a href="editEvent?id=${event.id}"><button>(Organizer) Edit</button></a>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>