<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <div style="position: relative">
          <img src="https://blog.ferplast.com/wp-content/uploads/2019/05/Aoshima-isola-gatti-giappone-viaggiare-viaggio-vacanza-mare.jpg" alt="Poster of the event" style="width: 100%; height: 400pt; object-fit: cover;"/>
<%--          <img src="${event.poster}" alt="Poster of the event" style="width: 100%; height: 300pt; object-fit: cover;"/>--%>
          <%--          <img src="${event.thumbnail}" alt="Image of the event" width="200" height="200"/>--%>
          <img src="https://www.focusjunior.it/content/uploads/2022/06/GettyImages-1292576425-e1655362380705.jpg" alt="Image of the event" style="height: 200pt; width: 200pt; object-fit: cover; position: absolute; bottom: 10pt; left: 10pt; border-radius: 5px; border: 10pt solid white"/>
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


</div>
<%@include file="/html/footer.html"%>
</body>
</html>