<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Create Event</title>
</head>

<body>
<%@include file="navbar.jsp"%>


    <div class="container">
        <div >
            <p>
                <c:out value="${message.message}"/>
            </p>
        </div>

        <div class="row justify-content-center my-4">
            <div class="col-xxl-10 col-12">
                <div class="card text-center border-cyan">
                    <div class="bg-cyan text-white">
                        <h1 class="page-title p-2">Create Event</h1>
                    </div>
                    <div class="form my-4 card-body">
                                <form action="<c:url value="/create-event"/>" id="createEventForm" method="POST" enctype="multipart/form-data" >

                                    <!--Name-->
                                    <div class="form-floating mb-3 pb-2">
                                            <input type="text" name="name" id="name" placeholder="Event Name.." class="form-control" required>
                                            <label for="name">Event's name </label>
                                    </div>

                                    <!--Description-->
                                    <div class="form-floating mb-3 pb-2">
                                        <textarea class="form-control" id="description" type="text" name="description" id="description"  placeholder="Description.." rows="3"></textarea>
                                        <label for="description">Description:</label>
                                    </div>
                                    <div class="row d-flex justify-content-around mb-4 align-items-center">
                                        <div class="col-lg-5 col-md-6 col-sm-12">
                                            <!--Price-->
                                            <div class="form-floating mb-3 pb-2">
                                                <input class="form-control" type="number" name="price" id="price" min="0" placeholder="0" step=".01" required>
                                                <label for="price">How much does the event cost?</label>
                                            </div>
                                            <!--Visibility-->
                                            <div class="form-floating mb-3 pb-2">
                                                <select class="form-select" name="visibility" id="visibility" required>
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
                                                        <input class="form-check-input" type="checkbox" id="${cause}" name="cs_${cause.id}" value="${cause.name}">
                                                        <label class="form-check-label" for="${cause.name}">${cause.name}</label><br>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--Location-->
                                    <div class="mt-4 pb-2">
                                        <label class="form-label mb-4">Where does it take place?</label>
                                        <div class="row">
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" name="city" id="city" placeholder="Enter city..." required>
                                                    <label for="city">Enter city.. </label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" name="street" id="street" placeholder="Enter Street..." required>
                                                    <label for="street">Enter street..</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <div class="form-floating mb-3 pb-2">
                                                    <input type="text" class="form-control" name="number" id="number" placeholder="Enter house number..." required>
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
                                                    <input class="form-control" type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" min="0" placeholder="0" required>
                                                    <label for="maxParticipantsInternational">Number of Max International participants:</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--MaxParticipantsVolunteer-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" min="0" placeholder="0" required>
                                                    <label for="maxParticipantsVolunteer">Number of Max Volunteer participants:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row d-flex justify-content-around mb-4">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--eventStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventStart" id="eventStart" required>
                                                    <label for="eventStart">When does the event start?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--eventEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" required>
                                                    <label for="eventEnd">When does the event end?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row pb-2">
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--subscriptionStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" required>
                                                    <label for="subscriptionStart">When can the subscription start?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--subscriptionEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" required>
                                                    <label for="subscriptionEnd">When can the subscription end?</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--withdrawalEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" required>
                                                    <label for="withdrawalEnd">When does the withdrawal End?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row d-flex justify-content-around mb-4">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--maxWaitingList-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" min="0" placeholder="0" required>
                                                    <label for="maxWaitingList">Insert the number of the size of the waiting list:</label>
                                                </div>
                                            </div>

                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--attributes-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="text" name="attributes" id="attributes" placeholder=" " required>
                                                    <label for="attributes">Attributes:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row d-flex justify-content-center">
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--thumbnail-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="thumbnail">Thumbnail:</label>
                                                    <input type="file" class="form-control" id="thumbnail" name="thumbnail" required>
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-6 col-sm-12">
                                                <!--poster-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="poster">Poster:</label>
                                                    <input type="file" class="form-control" id="poster" name="poster" required/>
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
                </div>
            </div>
        </div>


    </div>
    <%@include file="/html/footer.html"%>
</body>
</html>