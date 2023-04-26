<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Event detail</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="title">
  <h1>Event detail</h1>
  <hr>
</div>

<div class="container">
  <div>
    <div>
        <h3>Thumbnail</h3>
        <img src="${event.thumbnail}" width="200" height="200"/>
    </div>
    <div>
        <h3>Poster</h3>
        <img src="${event.poster}" width="1280" height="300"/>
    </div>
    <h2>${event.name}</h2>
    <hr>
    <p>
      ${event.description}
    </p>
    <p>Cost: ${event.price}</p>
    <p>Current participants: ${nParticipants}</p>
    <p>Waiting List: ${nWaiting}</p>
    <p>Location: ${event.location}</p>
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

</body>
</html>