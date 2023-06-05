<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Create Event</title>
    <%@ include file="../html/favicon.html"%>
    <script type="text/javascript" src="<c:url value="/js/validate_dates.js"/>"></script>
    <%@ include file="../jsp/cdn.jsp"%>
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
                <div class="card text-center border-magenta">
                    <div class="bg-magenta text-white">
                        <h1 class="page-title text-center p-2">Create Event</h1>
                    </div>
                    <div class="form my-4 card-body">
                                <form action="<c:url value="/create-event"/>" id="createEventForm" method="POST" enctype="multipart/form-data" >

                                    <!--Name-->
                                    <div class="mb-3 pb-2">
                                        <div class="d-flex justify-content-start">
                                            <label for="name">Event's name </label>
                                        </div>
                                        <div class="input-container">
                                            <input type="text" maxlength="127" name="name" id="name" placeholder="Event Name.." class="form-control" required>
                                        </div>
                                    </div>

                                    <!--Description-->
                                    <div class="mb-3 pb-2">
                                        <div class="d-flex justify-content-start">
                                            <label for="description">Description:</label>
                                        </div>
                                        <div class="input-container">
                                            <textarea class="form-control" id="description" type="text" name="description" id="description"  placeholder="Description.." rows="3"></textarea>
                                        </div>
                                    </div>
                                    <div class="row d-flex justify-content-start mb-4">
                                        <div class="col-md-6 col-sm-12">
                                            <!--Price-->
                                            <div class="mb-3 pb-2">
                                                <div class="d-flex justify-content-start">
                                                    <label for="price">How much does the event cost?</label>
                                                </div>
                                                <div class="input-container">
                                                    <input class="form-control" type="number" name="price" id="price" min="0" max="1000000" placeholder="0" step=".01" required>
                                                </div>
                                            </div>
                                            <!--Visibility-->
                                            <div class="mb-3 pb-2">
                                                <div class="d-flex justify-content-start">
                                                    <label for="visibility">To whom do you want to make it visible?</label>
                                                </div>
                                                <select class="form-select" name="visibility" id="visibility" required>
                                                  <option value="0">Tier 0 Users</option>
                                                  <option value="1">Tier 1 Users</option>
                                                  <option value="2">Tier 2 Users</option>
                                                  <option value="3">Tier 3 Users</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <!--Causes-->
                                            <div class="d-flex justify-content-start">
                                                <label>Which causes are included?</label><br>
                                            </div>
                                            <div class="mb-3 pb-2">
                                                <div class="form-check max-height-20">
                                                    <c:forEach items="${causes}" var="cause">
                                                        <input class="form-check-input" type="checkbox" id="${cause}" name="cs_${cause.id}" value="${cause.name}">
                                                        <label class="form-check-label" for="${cause.name}">${cause.name}</label><br>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <!--Tags-->
                                            <div class="mb-3 pb-2">
                                                <div class="d-flex justify-content-start">
                                                    <label for="tags">Tags (Separated by comma):</label>
                                                </div>
                                                <div class="input-container">
                                                    <input class="form-control" type="text" maxlength="255" name="tags" id="tags" placeholder="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!--Location-->
                                    <div class="mt-4 pb-2">
                                        <div class="d-flex justify-content-start">
                                            <label class="form-label mb-4">Where does it take place?</label>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-3 col-md-6 col-sm-12">
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="city">Enter city.. </label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input type="text" maxlength="60" class="form-control" name="city" id="city" placeholder="Enter city..." required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-12">
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="street">Enter street..</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input type="text" maxlength="100" class="form-control" name="street" id="street" placeholder="Enter Street..." required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-12">
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="street">Enter house number...</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input type="text" maxlength="5" class="form-control" name="number" id="number" placeholder="Enter house number...">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-12">
                                                <div class="mb-3 pb-2">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="street">Enter country...</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input type="text" class="form-control" name="country" id="country" placeholder="Enter country..." required>
                                                    </div>
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
                                                        <label for="maxParticipantsInternational">Number of Max International participants:</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" min="0" max="100000" placeholder="0" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <!--MaxParticipantsVolunteer-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="maxParticipantsVolunteer">Number of Max Volunteer participants:</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" min="0" max="100000" placeholder="0" required>
                                                    </div>
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
                                                    <div class="input-container">
                                                        <input class="form-control" type="datetime-local" name="eventStart" id="eventStart" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <!--eventEnd-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="eventEnd">When does the event end?</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" onfocusout="validateDates()" required>
                                                    </div>
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
                                                    <div class="input-container">
                                                        <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--subscriptionEnd-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="subscriptionEnd">When can the subscription end?</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" onfocusout="validateDates()" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-4 col-md-6 col-sm-12">
                                                <!--withdrawalEnd-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="withdrawalEnd">When does the withdrawal End?</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" onfocusout="validateDates()" required>
                                                    </div>
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
                                                    <div class="input-container">
                                                        <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" min="0" max="100000" placeholder="0" required>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-6 col-sm-12">
                                                <!--attributes-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label for="attributes">Extra infos required (separated by comma):</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input class="form-control" type="text" maxlength="255" name="attributes" id="attributes" placeholder="">
                                                    </div>
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
                                                    <div class="input-container">
                                                        <input type="file" accept="image/*" class="form-control" id="thumbnail" name="thumbnail" required>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <!--poster-->
                                                <div class="mb-3">
                                                    <div class="d-flex justify-content-start">
                                                        <label class="form-label" for="poster">Poster:</label>
                                                    </div>
                                                    <div class="input-container">
                                                        <input type="file" accept="image/*" class="form-control" id="poster" name="poster" required/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row justify-content-space-between">
                                        <div class="col-lg-3 col-md-6 col-sm-12 mb-2">
                                            <button type="reset" class="button btn color-magenta bg-white border-magenta px-4 py-2">Reset the form</button>
                                        </div>
                                        <div class="col-lg-3 col-md-6 col-sm-12">
                                            <button type="submit" class="button btn bg-magenta text-white border-magenta px-4 py-2">Continue</button>
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