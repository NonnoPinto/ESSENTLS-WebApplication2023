<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventStart}" var="dateStart"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventEnd}" var="dateEnd"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<%@ include file="../html/cdn.html" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="description" content="ESN Padova application">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="ISO-8859-1">
	<title>Event detail</title>
	<style>
        #event_card #event_header {
            height: 220pt;
            width: 100%;
            background-size: cover;
        }


	</style>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="containter">
	<div class="row justify-content-center my-4">
		<div class="col-10">
			<div class="card" id="event_card">
				<div class="card-img-top position-relative" id="event_header"
					 style="background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.35)), url('https://blog.ferplast.com/wp-content/uploads/2019/05/Aoshima-isola-gatti-giappone-viaggiare-viaggio-vacanza-mare.jpg');">
					<%--          <img src="${event.poster}" alt="Poster of the event" style="width: 100%; height: 300pt; object-fit: cover;"/>--%>
					<%--          <img src="${event.thumbnail}" alt="Image of the event" width="200" height="200"/>--%>
					<img src="https://www.focusjunior.it/content/uploads/2022/06/GettyImages-1292576425-e1655362380705.jpg"
						 alt="Image of the event" class="shadow-lg rounded"
						 style="height: 180pt; width: 180pt; object-fit: cover; margin-bottom: 20pt; margin-left: 20pt; position: absolute; bottom: 0"/>
					<div class="position-absolute" style="top: 20pt; left: 220pt; position: absolute; color: white">
						<h2 style="color: white; font-size: 30pt;">${event.name}</h2>
						<br/>
						<p>
							<i class="bi bi-geo-alt" style="color: white"></i>
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
							<i class="bi bi-calendar-event" style="color: white"></i>
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
					</div>
					<div class="card" style="position: absolute; top: 50%; transform: translateY(-50%); right: 50pt;width: 200pt">
						<div class="card-body">
							<p style="font-size: 20pt; text-align: center;">
								<c:choose>
									<c:when test="${event.price > 0}">
										&euro; ${event.price}
									</c:when>
									<c:otherwise>
										Free
									</c:otherwise>
								</c:choose>
							</p>

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
											<a class="btn bg-orange text-white border-orange px-4 py-2"
											   style="width: 100%"
											   href="payment?action=event&id=${event.id}">
												JOIN
											</a>
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
				<div class="card-body">
					<h3>Details</h3>
					<hr>
					<p>
						${event.description}
					</p>
				</div>
			</div>
		</div>


	</div>
	<%@include file="/html/footer.html" %>
</body>
</html>