<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Edit Event</title>
        <%@ include file="../html/favicon.html"%>
        <script type="text/javascript" src="<c:url value="/js/validate_dates.js"/>"></script>
        <%@ include file="../html/cdn.html"%>
    </head>

    <body>
        <%@include file="navbar.jsp"%>

        <div class="container">
            <div class="row justify-content-center my-4">
                <div class="col-xxl-10 col-12">
                    <div class="card text-center border-cyan">
                        <c:choose>
                            <c:when test="${empty event}">
                                <div class="alert alert-danger" role="alert">
                                    Event not found!
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="bg-cyan text-white">
                                    <h7 class="page-title p-2">Edit event</h7>
                                    <h2 class="page-title p-2"><c:out value="${event.name}"/></h2>
                                </div>
                                <p class="py-2"><c:out value="Event ID: ${event.id}"/></p>
                                <div class="form my-4 card-body">
                                    <form action="<c:url value="/editEvent"/>" id="editEventForm" method="POST" enctype="multipart/form-data" >

                                        <!--Name-->
                                        <div class="form-floating mb-3 pb-2">
                                            <input type="text" name="name" id="name" value="${event.name}" placeholder="${event.name}" class="form-control" maxlength="127" required>
                                            <label for="name">Name (currently ${event.name}): </label>
                                        </div>

                                        <!--Description-->
                                        <div class="form-floating mb-3 pb-2">
                                            <textarea class="form-control" id="description" type="text" name="description" id="description" placeholder="Description: " rows="3">${event.description}</textarea>
                                            <label for="description">Description:</label>
                                        </div>
                                        <div class="row d-flex justify-content-around mb-4 align-items-center">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--Price-->
                                                <div class="form-floating mb-3 pb-2">
                                                    <input class="form-control" type="number" name="price" id="price" min="0" max="1000000" placeholder="0" step=".01" value="${event.price}" required>
                                                    <label for="price">Price (currently ${event.price}):</label>
                                                </div>
                                                <!--Visibility-->
                                                <div class="form-floating mb-3 pb-2">
                                                    <select class="form-select" name="visibility" id="visibility" required>
                                                        <option value="${event.visibility}" selected disabled hidden>Tier ${event.visibility} Users</option>
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
                                                    <label>Which causes are included?</label><br>
                                                    <div class="form-check max-height-20">
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
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="form-floating mb-3 pb-2">
                                                        <input type="text" class="form-control" value="${event.locationMap.city}" name="city" id="city" placeholder="Enter city..." maxlength="60" required>
                                                        <label for="city">Enter city..</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="form-floating mb-3 pb-2">
                                                        <input type="text" class="form-control" value="${event.locationMap.street}" name="street" id="street" placeholder="Enter Street..." maxlength="100" required>
                                                        <label for="street">Enter street..</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="form-floating mb-3 pb-2">
                                                        <input type="text" class="form-control" value="${event.locationMap.number}" maxlength="5" name="number" id="number" placeholder="Enter house number...">
                                                        <label for="street">Enter house number...</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="form-floating mb-3 pb-2">
                                                        <input type="text" class="form-control" value="${event.locationMap.country}" name="country" id="country" placeholder="Enter country..." required>
                                                        <label for="street">Enter country...</label>
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
                                                        <input class="form-control" type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" value="${event.maxParticipantsInternational}" min="0" max="100000" placeholder="0" required>
                                                        <label for="maxParticipantsInternational"># Max International participants:</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--MaxParticipantsVolunteer-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" value="${event.maxParticipantsVolunteer}" min="0" max="100000" placeholder="0" required>
                                                        <label for="maxParticipantsVolunteer"># Max Volunteer participants:</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row d-flex justify-content-around mb-4">
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--eventStart-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="datetime-local" name="eventStart" id="eventStart" value="${event.eventStart}" onfocusout="validateDates()" required>
                                                        <label for="eventStart">When does the event start?</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--eventEnd-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" value="${event.eventEnd}" onfocusout="validateDates()" required>
                                                        <label for="eventEnd">When does the event end?</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row pb-2">
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--subscriptionStart-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" value="${event.subscriptionStart}" onfocusout="validateDates()" required>
                                                        <label for="subscriptionStart">When can the subscription start?</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--subscriptionEnd-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" value="${event.subscriptionEnd}" onfocusout="validateDates()" required>
                                                        <label for="subscriptionEnd">When can the subscription end?</label>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--withdrawalEnd-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" value="${event.withdrawalEnd}" onfocusout="validateDates()" required>
                                                        <label for="withdrawalEnd">When does the withdrawal End?</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row d-flex justify-content-around mb-4">
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--maxWaitingList-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" value="${event.maxWaitingList}" min="0" placeholder="0" max="100000" required>
                                                        <label for="maxWaitingList">Insert the number of the size of the waiting list:</label>
                                                    </div>
                                                </div>

                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--attributes-->
                                                    <div class="form-floating mb-3">
                                                        <input class="form-control" type="text" name="attributes" id="attributes" value="${event.attributes_asString}" placeholder="${event.attributes_asString}" maxlength="255">
                                                        <label for="attributes">Attributes:</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row d-flex justify-content-center">
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--thumbnail-->
                                                    <div class="mb-3">
                                                        <label class="form-label" for="thumbnail">Thumbnail:</label>
                                                        <c:if test="${not empty event.thumbnail}">
                                                            <!-- TODO: default .png to add in folder -->
                                                            <img src="${event.thumbnail}" class="img-fluid mb-3"/>
                                                        </c:if>
                                                        <input type="file" class="form-control" id="thumbnail" name="thumbnail" accept="image/*" value="${thumbnail}">
                                                    </div>
                                                </div>
                                                <div class="col-lg-5 col-md-6 col-sm-12">
                                                    <!--poster-->
                                                    <div class="mb-3">
                                                        <label class="form-label" for="poster">Poster:</label>
                                                        <c:if test="${!event.poster.equals('')}">
                                                            <img src="${event.poster}" class="img-fluid mb-3"/>
                                                        </c:if>
                                                        <input type="file" class="form-control" id="poster" name="poster" accept="image/*" value="${poster}"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row justify-content-center">
                                            <div class="col-lg-3 col-md-6 col-sm-12 p-1">
                                                <button type="reset" class="button bg-white color-cyan border-cyan px-4 py-2">Undo changes</button>
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-12 p-1">
                                                <button type="submit" class="button bg-cyan text-white border-cyan px-4 py-2">Continue</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>