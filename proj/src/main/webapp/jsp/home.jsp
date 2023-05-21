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


                <form class="input-group mb-3" action="">

                    <select class="form-select" aria-label="Tag filter dropdown" name="tag">
                        <c:choose>
                            <c:when test="${tag eq ''}">
                                <option value="" selected hidden>Tag</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${tag}" selected hidden>${tag}</option>
                            </c:otherwise>
                        </c:choose>
                        <option value="">---no tag---</option>
                        <c:forEach items="${tags}" var="tag" varStatus="loop">
                            <option value="${tag.getName()}"><c:out value="${tag.getName()}"/></option>
                        </c:forEach>
                    </select>
                    
                    <select class="form-select" aria-label="Tag filter dropdown" name="cause">
                        <c:choose>
                            <c:when test="${cause.getId() eq -1}">
                                <option value="" selected hidden>Cause</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cause.getId()}" selected hidden>${cause.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                        <option value="">---no cause---</option>
                        <c:forEach items="${causes}" var="cause" varStatus="loop">
                            <option value="${cause.getId()}"><c:out value="${cause.getName()}"/></option>
                        </c:forEach>
                    </select>

                    <div class="break_flex d-block d-lg-none my-2"></div>

                    <input type="search" id="srch" name="srch" placeholder="name,location or description" value="${srch}" class="form-control" aria-label="Text input">

                    <button type="submit" class="btn btn-primary bg-cyan">
                        <i class="fas fa-search"></i>
                    </button>
                </form>


                <c:forEach items="${events}" var="event" varStatus="loop">
                    <div class="border border-orange px-5 my-3 container_event" onclick="window.location='./eventdetail?id=${event.id}';">
                        <div class="container d-flex zindex-modal">          
                            <div class="row align-self-center w-100">
                                <div class="col-lg-8 col-12">
                                    <p class="card-text mb-2"><b class="fs-3 fw-bold me-2">${event.name}</b> · 
                                        <i class="bi bi-geo-alt ms-2"></i>&nbsp;
                                        <span style="text-transform: capitalize">
                                            ${event.locationMap.street},
                                        </span>
                                        <span style="text-transform: capitalize">
                                            ${event.locationMap.number}
                                        </span>
                                        <span style="text-transform: capitalize">
                                            ${event.locationMap.zip}
                                        </span>
                                        <span style="text-transform: capitalize">
                                            ${event.locationMap.city}
                                        </span>
                                        <span style="text-transform: capitalize">
                                            ${event.locationMap.country}
                                        </span>
                                    </p>
                                    <p class="card-text text-secondary">
                                        ${event.description}
                                    </p>
                                </div>
                                <div class="col-lg-4 text-end d-none d-lg-block">
                                    <p class="my-3">End subscription: <fmt:formatDate pattern="MMM dd · HH:mm" value="${event.subscriptionEnd}"/></p>
                                    <p class="my-3">Price: &euro; <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${event.price}"/></p>
                                </div>
                            </div>
                        </div>

                        <div class="container_event_bottom bg-orange">
                            <p class="m-0 pe-5 color-white">#${event.id}</p>
                        </div>
                    </div>


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