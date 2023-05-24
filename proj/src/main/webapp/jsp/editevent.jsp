<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Edit Event</title>
        <%@ include file="../html/favicon.html"%>
    </head>

    <body>
        <%@include file="navbar.jsp"%>

        <div class="container">
        <div class="row justify-content-center my-4">
            <div class="col-xxl-10 col-12">
                <div class="card text-center border-cyan">
                    <c:choose>
                        <c:when test="${event == null}">
                            <div class="alert alert-danger" role="alert">
                              Event not found!
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="bg-cyan text-white">
                                <h7 class="page-title p-2">Edit event</h7>
                                <h2 class="page-title p-2"><c:out value="${event.getName()}"/></h2>
                            </div>
                            <p><c:out value="Event ID: ${event.getId().toString()}"/></p>
                            <div class="form my-4 card-body">
                                <form action="<c:url value="/editEvent"/>" id="editEventForm" method="POST" enctype="multipart/form-data" >

                                    <!--Name-->
                                    <div class="form-floating mb-3 pb-2">
                                            <input type="text" name="name" id="name" value="${event.getName()}" placeholder="${event.getName()}" class="form-control" required>
                                            <label for="name">Name (currently ${event.getName()}): </label>
                                    </div>

                                    <!--Description-->
                                    <div class="form-floating mb-3 pb-2">
                                        <textarea class="form-control" id="description" type="text" name="description" id="description" value="${event.getDescription()}" placeholder="Description: " rows="3"></textarea>
                                        <label for="description">Description:</label>
                                    </div>
                                    <div class="row d-flex justify-content-around mb-4 align-items-center">
                                        <div class="col-lg-5 col-md-6 col-sm-12">
                                            <!--Price-->
                                            <div class="form-floating mb-3 pb-2">
                                                <input class="form-control" type="number" name="price" id="price" min="0" placeholder="0" step=".01" value="${event.getPrice()}" required>
                                                <label for="price">Price (currently ${event.getPrice()}):</label>
                                            </div>
                                            <!--Visibility-->
                                            <div class="form-floating mb-3 pb-2">
                                                <select class="form-select" name="visibility" id="visibility" required>
                                                  <option value="${event.getVisibility()}" selected disabled hidden>Tier ${event.getVisibility()} Users</option>
                                                  <option value="0">Tier 0 Users</option>
                                                  <option value="1">Tier 1 Users</option>
                                                  <option value="2">Tier 2 Users</option>
                                                  <option value="3">Tier 3 Users</option>
                                                </select>
                                                <label for="visibility">To whom do you want to make it visible?</label>
                                            </div>
                                        </div>
                                        <div class="col-lg-5 col-md-6 col-sm-12">
                                            <!--Causes-->
                                            <div class="mb-3 pb-2">
                                                <label for="">Which causes does the event include?</label><br>
                                                <div class="form-check">
                                                    <c:forEach items="${causes}" var="cause">
                                                        <c:set var="elementCause" scope="session" value="${cause.id}"/>
                                                        <c:choose>
                                                            <c:when test="${listCauses.contains(elementCause)}">
                                                                <input class="form-check-input" type="checkbox" id="${cause}" name="cs_${cause.id}" value="${cause.name}" checked>
                                                                <label class="form-check-label" for="${cause.name}">${cause.name}</label><br>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input class="form-check-input" type="checkbox" id="${cause}" name="cs_${cause.id}" value="${cause.name}">
                                                                <label class="form-check-label" for="${cause.name}">${cause.name}</label><br>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Location-->
                                    <div class="mb-3 pb-2">
                                        <label class="form-label mb-4">Where does it take place?</label>
                                        <div class="row">
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" value="${city}" name="city" id="city" placeholder="Enter city..." required>
                                                    <label for="city">Enter city..</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" value="${street}" name="street" id="street" placeholder="Enter Street..." required>
                                                    <label for="street">Enter street..</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" value="${number}" name="number" id="number" placeholder="Enter house number..." required>
                                                    <label for="street">Enter house number...</label>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div>
                                        <label class="form-label mb-4">Additional Information Required</label>
                                        <div class="row d-flex justify-content-around mb-4">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--maxParticipantsInternational-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" value="${event.getMaxParticipantsInternational()}" min="0" placeholder="0" required>
                                                    <label for="maxParticipantsInternational">Number of Max International participants:</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--MaxParticipantsVolunteer-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" value="${event.getMaxParticipantsVolunteer()}" min="0" placeholder="0" required>
                                                    <label for="maxParticipantsVolunteer">Number of Max Volunteer participants:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row d-flex justify-content-around mb-4">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--eventStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventStart" id="eventStart" value="${event.getEventStart()}" required>
                                                    <label for="eventStart">When does the event start?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--eventEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" value="${event.getEventEnd()}" required>
                                                    <label for="eventEnd">When does the event end?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row pb-2">
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--subscriptionStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" value="${event.getSubscriptionStart()}" required>
                                                    <label for="subscriptionStart">When can the subscription start?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--subscriptionEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" value="${event.getSubscriptionEnd()}" required>
                                                    <label for="subscriptionEnd">When can the subscription end?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--withdrawalEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" value="${event.getWithdrawalEnd()}" required>
                                                    <label for="withdrawalEnd">When does the withdrawal End?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row d-flex justify-content-around mb-4">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--maxWaitingList-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" value="${event.getMaxWaitingList()}" min="0" placeholder="0" required>
                                                    <label for="maxWaitingList">Insert the number of the size of the waiting list:</label>
                                                </div>
                                            </div>

                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--attributes-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="text" name="attributes" id="attributes" value="${event.getAttributes_asString()}" placeholder="${event.getAttributes_asString()}" required>
                                                    <label for="attributes">Attributes:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row d-flex justify-content-center">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--thumbnail-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="thumbnail">Thumbnail:</label>
                                                    <c:if test="${!event.thumbnail.equals('')}">
                                                        <img src="${event.thumbnail}" class="img-fluid mb-3"/>
                                                    </c:if>
                                                    <input type="file" class="form-control" id="thumbnail" name="thumbnail" value="${thumbnail}">
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--poster-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="poster">Poster:</label>
                                                    <c:if test="${!event.poster.equals('')}">
                                                        <img src="${event.poster}" class="img-fluid mb-3"/>
                                                    </c:if>
                                                    <input type="file" class="form-control" id="poster" name="poster" value="${poster}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row justify-content-center">
                                        <div class="col-lg-2 col-md-6 col-sm-12">
                                            <button type="reset" class="btn btn-secondary">Reset the form</button>
                                        </div>
                                        <div class="col-lg-2 col-md-6 col-sm-12">
                                            <button type="submit" class="btn btn-primary">Continue</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>