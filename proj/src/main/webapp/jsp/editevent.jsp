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
        <%@ include file="../jsp/cdn.jsp"%>
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
                                    <h1 class="page-title p-2">Edit event</h1>
                                    <h2 class="page-title p-2"><c:out value="${event.name}"/></h2>
                                </div>
                                <p class="py-2"><c:out value="Event ID: ${event.id}"/></p>
                                <div class="form my-4 card-body">
                                    <form action="<c:url value="/editEvent"/>" id="editEventForm" method="POST" enctype="multipart/form-data" >

                                        <!--Name-->
                                        <div class="mb-3 pb-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="name">Name (currently ${event.name}): </label>
                                            </div>
                                            <input type="text" name="name" id="name" value="${event.name}" placeholder="${event.name}" class="form-control" maxlength="127" required>
                                        </div>

                                        <!--Description-->
                                        <div class="mb-3 pb-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="description">Description:</label>
                                            </div>
                                            <div class="input-container">
                                                <textarea class="form-control" id="description" type="text" name="description" id="description" placeholder="Description: " rows="3">${event.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="row d-flex justify-content-start mb-4">
                                            <div class="col-md-6 col-sm-12">
                                                <!--Price-->
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="price">Price (currently ${event.price}):</label>
                                                    </div>
                                                    <input class="form-control" type="number" name="price" id="price" min="0" max="1000000" placeholder="0" step=".01" value="${event.price}" required>
                                                </div>
                                                <!--Visibility-->
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="visibility">To whom do you want to make it visible?</label>
                                                    </div>
                                                    <select class="form-select" name="visibility" id="visibility" required>
                                                        <option value="${event.visibility}" selected disabled hidden>Tier ${event.visibility} Users</option>
                                                        <option value="0">Tier 0 Users</option>
                                                        <option value="1">Tier 1 Users</option>
                                                        <option value="2">Tier 2 Users</option>
                                                        <option value="3">Tier 3 Users</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <!--Causes-->
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="causes">Which causes are included?</label>
                                                    </div>
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
                                            <div class="col-md-6 col-sm-12">
                                                <!--Tags-->
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label>Which tags are included?</label>
                                                    </div>
                                                    <div class="form-check max-height-20">
                                                        <c:forEach items="${tags}" var="tag">
                                                            <c:set var="elementTag" scope="session" value="${tag.name}"/>
                                                            <c:choose>
                                                                <c:when test="${listTags.contains(elementTag)}">
                                                                    <input class="form-check-input" type="checkbox" id="${tag}" name="cs_${tag.name}" value="${tag.name}" checked>
                                                                    <label class="form-check-label" for="${tag.name}">${tag.name}</label><br>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input class="form-check-input" type="checkbox" id="${tag}" name="cs_${tag.name}" value="${tag.name}">
                                                                    <label class="form-check-label" for="${tag.name}">${tag.name}</label><br>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--Location-->
                                        <div class="mb-3 pb-2">
                                            
                                            <div class="d-flex justify-content-start">
                                                <label class="form-label mb-4">Where does it take place?</label>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="mb-3 pb-2">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="city">Enter city..</label>
                                                        </div>
                                                        <input type="text" class="form-control" value="${event.locationMap.city}" name="city" id="city" placeholder="Enter city..." maxlength="60" required>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="mb-3 pb-2">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="street">Enter street..</label>
                                                        </div>
                                                        <input type="text" class="form-control" value="${event.locationMap.street}" name="street" id="street" placeholder="Enter Street..." maxlength="100" required>
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="mb-3 pb-2">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="street">Enter house number...</label>
                                                        </div>
                                                        <input type="text" class="form-control" value="${event.locationMap.number}" maxlength="5" name="number" id="number" placeholder="Enter house number...">
                                                    </div>
                                                </div>
                                                <div class="col-lg-3 col-md-6 col-sm-12">
                                                    <div class="mb-3 pb-2">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="street">Enter country...</label>
                                                        </div>
                                                        <input type="text" class="form-control" value="${event.locationMap.country}" name="country" id="country" placeholder="Enter country..." required>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div>
                                            
                                            <div class="d-flex justify-content-start">
                                                <label class="form-label mb-4">Additional Information Required</label>
                                            </div>
                                            <div class="row d-flex justify-content-start mb-4">
                                                <div class="col-md-6 col-sm-12">
                                                    <!--maxParticipantsInternational-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="maxParticipantsInternational"># Max International participants:</label>
                                                        </div>
                                                        <input class="form-control" type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" value="${event.maxParticipantsInternational}" min="0" max="100000" placeholder="0" required>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-sm-12">
                                                    <!--MaxParticipantsVolunteer-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="maxParticipantsVolunteer"># Max Volunteer participants:</label>
                                                        </div>
                                                        <input class="form-control" type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" value="${event.maxParticipantsVolunteer}" min="0" max="100000" placeholder="0" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row d-flex justify-content-start mb-4">
                                                <div class="col-md-6 col-sm-12">
                                                    <!--eventStart-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="eventStart">When does the event start?</label>
                                                        </div>
                                                        <input class="form-control" type="datetime-local" name="eventStart" id="eventStart" value="${event.eventStart}" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-sm-12">
                                                    <!--eventEnd-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="eventEnd">When does the event end?</label>
                                                        </div>
                                                        <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" value="${event.eventEnd}" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row pb-2">
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--subscriptionStart-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="subscriptionStart">When can the subscription start?</label>
                                                        </div>
                                                        <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" value="${event.subscriptionStart}" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--subscriptionEnd-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="subscriptionEnd">When can the subscription end?</label>
                                                        </div>
                                                        <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" value="${event.subscriptionEnd}" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <!--withdrawalEnd-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="withdrawalEnd">When does the withdrawal End?</label>
                                                        </div>
                                                        <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" value="${event.withdrawalEnd}" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row d-flex justify-content-start mb-4">
                                                <div class="col-md-6 col-sm-12">
                                                    <!--maxWaitingList-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="maxWaitingList">Insert the number of the size of the waiting list:</label>
                                                        </div>
                                                        <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" value="${event.maxWaitingList}" min="0" placeholder="0" max="100000" required>
                                                    </div>
                                                </div>

                                                <div class="col-md-6 col-sm-12">
                                                    <!--attributes-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label for="attributes">Attributes:</label>
                                                        </div>
                                                        <input class="form-control" type="text" name="attributes" id="attributes" value="${event.attributes_asString}" placeholder="${event.attributes_asString}" maxlength="255">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row d-flex justify-content-center">
                                                <div class="col-md-6 col-sm-12">
                                                    <!--thumbnail-->
                                                    <div class="mb-3">
                                                        <div class="d-flex justify-content-start">
                                                            <label class="form-label" for="thumbnail">Thumbnail:</label>
                                                        </div>
                                                        <c:if test="${not empty event.thumbnail}">
                                                            <img src="${event.thumbnail}" class="img-fluid mb-3"/>
                                                        </c:if>
                                                        <input type="file" class="form-control" id="thumbnail" name="thumbnail" accept="image/*" value="${thumbnail}">
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-sm-12">
                                                    <!--poster-->
                                                    <div class="mb-3">
                                                        
                                                        <div class="d-flex justify-content-start">
                                                            <label class="form-label" for="poster">Poster:</label>
                                                        </div>
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
                                                <button type="reset" class="button btn bg-white color-cyan border-cyan px-4 py-2">Undo changes</button>
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-12 p-1">
                                                <button type="submit" class="button btn bg-cyan text-white border-cyan px-4 py-2">Continue</button>
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