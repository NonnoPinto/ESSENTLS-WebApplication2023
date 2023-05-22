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
                                <div class="card h-100 container" onclick="window.location='./eventdetail?id=${event.id}';">
                                    <img class="card-img-top event-preview-image" src="media/${event.thumbnail}" alt="Event thumbnail">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-3 column-content-vertical-center">
                                                <p class="event-date text-center text-capitalize m-0 p-0">
                                                    <fmt:formatDate pattern="MMM dd" value="${event.eventStart}"/>
                                                </p>
                                            </div>
                                            <div class="col-9 event-infos">
                                                <p class="event-title m-0 p-0">
                                                    ${event.name}
                                                </p>
                                                <p class="event-price m-0 p-0">
                                                    &euro; <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${event.price}"/>
                                                </p>
                                                <p class="event-location m-0 p-0">
                                                        <i class="bi bi-geo-alt"></i>
                                                        <span class="text-capitalize">
                                                            ${event.location.getString("city")},
                                                        </span>
                                                        <span class="text-capitalize">
                                                            ${event.location.getString("country")}
                                                        </span>
                                                </p>
                                            </div>
                                        </div>
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