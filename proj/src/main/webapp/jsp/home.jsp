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
        <%@ include file="../html/favicon.html"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        
        <c:choose>
            <c:when test="${message.isError()}">
                <ul>
                    <li>message: <c:out value="${message.message}"/></li>
                </ul>
            </c:when>
            <c:otherwise>
            
                <div class="bg-image rounded-3 home_banner" >

                    <div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
                        <div class="d-flex flex-column justify-content-center align-items-center h-100 container">

                            <h1 class="mb-5 color-white">Exclusive events, priceless moments!</h1>


                            <form class="input-group my-3" action="">

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


                        </div>
                    </div>
                </div>

                <div class="container">    
                    

                    <c:forEach items="${events}" var="event" varStatus="loop">

                        <c:choose>
                            <c:when test="${loop.index lt 3}">
                                <c:if test="${loop.index eq 0}">
                                    <div class="row justify-content-evenly">
                                </c:if>
                                <div class="col-12 col-md-4 mb-4">
                                    <div class="card h-100 container home_card border-orange" onclick="window.location='./eventdetail?id=${event.id}';">
                                        <img class="card-img-top img-fluid event-preview-image" src="${event.thumbnail}" alt="Event thumbnail">
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
                                <c:if test="${loop.index eq 2}">
                                    </div>
                                </c:if>
                            </c:when>
                            <c:otherwise>

                                <fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventStart}" var="dateStart"/>
                                <fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventEnd}" var="dateEnd"/>
                                <div class="border border-orange px-5 my-3 container_event" onclick="window.location='./eventdetail?id=${event.id}';">
                                    <div class="container d-flex zindex-modal">          
                                        <div class="row align-self-center w-100">
                                            <div class="col-lg-7 col-12">
                                                <p class="card-text mb-2"><b class="fs-3 fw-bold me-2">${event.name}</b> 
                                                    <span class="capital-text d-none d-lg-inline-block">
                                                        · <i class="bi bi-geo-alt ms-2"></i>&nbsp;
                                                        ${event.locationMap.street},
                                                        ${event.locationMap.number}
                                                        ${event.locationMap.zip}
                                                        ${event.locationMap.city}
                                                        ${event.locationMap.country}
                                                    </span>
                                                </p>
                                                <p class="mb-2 d-block d-lg-none fs-7">
                                                    <i class="bi bi-geo-alt"></i>&nbsp;
                                                    <span class="capital-text">
                                                        ${event.locationMap.street},
                                                    </span>
                                                    <span class="capital-text">
                                                        ${event.locationMap.number}
                                                    </span>
                                                    <span class="capital-text">
                                                        ${event.locationMap.zip}
                                                    </span>
                                                    <span class="capital-text">
                                                        ${event.locationMap.city}
                                                    </span>
                                                    <span class="capital-text">
                                                        ${event.locationMap.country}
                                                    </span>
                                                </p>
                                                <p class="mb-2 d-block d-lg-none fs-7">
                                                    <i class="bi bi-calendar-event"></i>&nbsp;
                                                    <fmt:formatDate pattern="dd/MMM/yy · HH:mm" value="${event.eventStart}"/>
                                                    <span>-</span>
                                                    <c:choose>
                                                        <c:when test="${dateStart eq dateEnd}">
                                                            <fmt:formatDate pattern="HH:mm" value="${event.eventEnd}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate pattern="dd/MMM · HH:mm" value="${event.eventEnd}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <p class="card-text text-secondary mb-2">
                                                    ${event.description}
                                                </p>
                                            </div>
                                            <div class="col-lg-5 text-end d-none d-lg-block">
                                                <p class="mb-2 d-none d-lg-block">
                                                    <i class="bi bi-calendar-event"></i>&nbsp;
                                                    <fmt:formatDate pattern="dd/MMM/yy · HH:mm" value="${event.eventStart}"/>
                                                    <span>-</span>
                                                    <c:choose>
                                                        <c:when test="${dateStart eq dateEnd}">
                                                            <fmt:formatDate pattern="HH:mm" value="${event.eventEnd}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <fmt:formatDate pattern="dd/MMM · HH:mm" value="${event.eventEnd}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <p class="my-2">End subscription: <fmt:formatDate pattern="MMM dd · HH:mm" value="${event.subscriptionEnd}"/></p>
                                                <p class="my-2">Price: &euro; <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${event.price}"/></p>
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
                                            <a href="eventparticipants?id=${event.id}"><button class="button color-cyan border-cyan bg-white px-3 py-1">(Organizer) Participants List</button></a>
                                            <a href="editEvent?id=${event.id}"><button class="button color-cyan border-cyan bg-white px-3 py-1">(Organizer) Edit</button></a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

        <%@include file="/html/footer.html"%>
    </body>
</html>