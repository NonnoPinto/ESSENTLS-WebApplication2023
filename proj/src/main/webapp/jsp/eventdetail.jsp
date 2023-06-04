<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<fmt:setLocale value="en_US"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventStart}" var="dateStart"/>
<fmt:formatDate pattern="MMMM dd, yyyy" value="${event.eventEnd}" var="dateEnd"/>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="description" content="ESN Padova application">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="ISO-8859-1">
	<title>Event detail</title>
    <%@ include file="../html/favicon.html"%>
	<%@ include file="../html/cdn.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container">
	<div class="row justify-content-center my-4">
		<div class="col-xxl-10 col-12">
			<div class="card" id="event_card">
				<div class="card-img-top position-relative" id="event_header"
					 style="background-image: url('${event.poster}');">
					<div class="clearfix">
					    <c:choose>

                           <c:when test= "${empty event.thumbnail}">
                              <img src="media/default_thumbnail.png"
                                 alt="Image of the event" class="rounded float-sm-start"
                                 id="event_icon"/>
                           </c:when>

                           <c:otherwise>
                              <img src="${event.thumbnail}"
                               alt="Image of the event" class="rounded float-sm-start"
                               id="event_icon"/>
                           </c:otherwise>
                        </c:choose>
						<div id="event_info" class="float-sm-start">
							<h2>${event.name}</h2>
							<br/>
							<p>
								<i class="bi bi-geo-alt"></i>&nbsp;
								<span class="text-capitalize">
									${event.locationMap.street},
								</span>
								<span class="text-capitalize">
									${event.locationMap.number}
								</span>
								<span class="text-capitalize">
									${event.locationMap.zip}
								</span>
								<span class="text-capitalize">
									${event.locationMap.city}
								</span>
								<span class="text-capitalize">
									${event.locationMap.country}
								</span>
							</p>
							<p>
								<i class="bi bi-calendar-event"></i>&nbsp;
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
						<div class="card float-lg-end float-md-end" id="event_buy">
							<div class="card-body">
								<p>
									<c:choose>
										<c:when test="${event.price > 0}">
											&euro; <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${event.price}"/>
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
												<button class="btn bg-orange text-white border-orange px-4 py-2 w-100"
												   onclick="window.location.href='payment?action=event&id=${event.id}'">
													JOIN
												</button>
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
				</div>
				<div class="card-body">
					<h3>Details</h3>
					<hr>
					<p>
						${event.description}
					</p>
					<c:set var="full_address" value="${event.locationMap.street} ${event.locationMap.number} ${event.locationMap.zip} ${event.locationMap.city} ${event.locationMap.country}" />
					<iframe
							width="600"
							height="450"
							style="border:0"
							loading="lazy"
							allowfullscreen
							referrerpolicy="no-referrer-when-downgrade"
							src="https://www.google.com/maps/embed/v1/place?key=AIzaSyD4iE2xVSpkLLOXoyqT-RuPwURN3ddScAI
    						&q=${fn:replace(full_address, ' ', '+')}">
					</iframe>
					<img src="${event.poster}" class="w-100 rounded mx-auto d-block">
				</div>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${canEditEvent}">
			<div class="row justify-content-center my-2">
				<div class="col-xxl-10 col-12 d-flex justify-content-end">
					<form action="<c:url value="/editEvent"/>" method="GET">
						<input name="id" value="${event.id}" type="hidden">
						<button type="submit" class="button bg-orange border-orange color-white px-4 py-2">Edit Event</button>
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>