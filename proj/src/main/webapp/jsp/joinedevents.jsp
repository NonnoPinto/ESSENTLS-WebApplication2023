<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../html/cdn.html"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Event List</title>
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
                    <h1 class="page-title text-center p-2">Your events!</h1>
                    <div class="row justify-content-start">
                        <c:forEach items="${events}" var="event" varStatus="loop">
                            <div class="col-lg-3 col-md-6 col-sm-12 my-2">
                                <div class="card border-black container">
                                    <img class="card-img-top event-preview-image" src="media/${event.thumbnail}" alt="Event thumbnail">
                                    <div class="card-body">
                                        ${event.name}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>