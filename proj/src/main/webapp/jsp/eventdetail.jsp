<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value = "en_US"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventStart}" var="dateStart"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventEnd}" var="dateEnd"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Event detail</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="title">
  <h1>Event detail</h1>
  <hr>
</div>

<div class="containter">
  <div class="row justify-content-center my-4">
    <div class="col-10">
      <div style="position: relative; height: 220pt; width: 100%; background-size: cover; background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.35)), url('https://blog.ferplast.com/wp-content/uploads/2019/05/Aoshima-isola-gatti-giappone-viaggiare-viaggio-vacanza-mare.jpg');">
<%--          <img src="${event.poster}" alt="Poster of the event" style="width: 100%; height: 300pt; object-fit: cover;"/>--%>
          <%--          <img src="${event.thumbnail}" alt="Image of the event" width="200" height="200"/>--%>
        <div style="position: absolute; width: 100%; bottom: 0;">
          <img src="https://www.focusjunior.it/content/uploads/2022/06/GettyImages-1292576425-e1655362380705.jpg" alt="Image of the event" style="height: 200pt; width: 200pt; object-fit: cover; margin-bottom: 10pt; margin-left: 10pt; border-radius: 5px; border: 8pt solid white; float: left"/>
        </div>
      </div>
      <hr>
      <h2>${event.name}</h2>
      <p>
        <i class="bi bi-geo-alt"></i>
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
      <p>
        <i class="bi bi-calendar-event"></i>
        <fmt:formatDate pattern="MMMM dd, yyyy · HH:mm" value="${event.eventStart}"/>
        <span>-</span>
        <c:choose>
          <c:when test="${dateStart eq dateEnd}">
            <fmt:formatDate pattern="HH:mm" value="${event.eventEnd}"/>
          </c:when>
          <c:otherwise>
            <fmt:formatDate pattern="MMMM dd, yyyy · HH:mm" value="${event.eventEnd}"/>
          </c:otherwise>
        </c:choose>
      </p>
      <p>
        ${event.description}
      </p>
      <p>Cost: ${event.price}</p>
      <p>Current participants: ${nParticipants}</p>
      <p>Waiting List: ${nWaiting}</p>
      <c:choose>
        <c:when test="${currentIsPartecipating}">
          You are partecipating at the event.
        </c:when>
        <c:when test="${currentIsWaiting}">
          You are actually in the waiting list for this event.
        </c:when>
        <c:otherwise>
          <c:choose>
            <c:when test="${(nParticipants < event.maxParticipantsInternational) || (nWaiting < event.maxWaitingList)}">
              <a href="payment?action=event&id=${event.id}"><button>Join the event!</button></a>
            </c:when>
            <c:otherwise>
              Sorry, the event is full.
            </c:otherwise>
          </c:choose>
        </c:otherwise>
      </c:choose>
    </div>
  </div>


</div>
<%@include file="/html/footer.html"%>
</body>
</html>