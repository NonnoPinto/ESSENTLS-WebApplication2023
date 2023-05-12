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


    <div class="containter">


        <div >
            <p>
                <c:out value="${message.message}"/>
            </p>
        </div>

        <div class="row justify-content-center my-4">
            <div class="col-md-8">
                <div class="card text-center border-cyan">
                    <div class="bg-cyan text-white">
                        <h1 class="page-title p-2">Create Event</h1>
                    </div>
                    <div class="form my-4 card-body">
                                <form action="<c:url value="/create-event"/>" id="createEventForm" method="POST" enctype="multipart/form-data" >

                                    <!--Name-->
                                    <div class="form-floating mb-3 pb-2">
                                            <input type="text" name="name" id="floatingInput" placeholder="Event Name.." class="form-control" required>
                                            <label for="floatingInput"> Event's name </label>
                                    </div>

                                    <!--Description-->
                                    <div class="form-floating mb-3 pb-2">
                                        <input class="form-control" id="exampleFormControlTextarea1" rows="3" type="text" name="description" id="floatingInput"  placeholder="Description.." required>
                                        <label for="floatingInput">Some details:</label>
                                    </div>
                                    <div class="row d-flex justify-content-center">
                                        <div class="col">
                                            <!--Price-->
                                            <div class="form-floating mb-3 pb-2">
                                                <input class="form-control" type="number" name="price" id="floatingInput" min="0" placeholder="0" step=".01" required>
                                                <label for="floatingInput">How much does the event cost?</label>
                                            </div>
                                            <!--Visibility-->
                                            <div style="form-floating mb-3 pb-2">
                                                <label for="">To whom do you want to make it visible?</label>
                                                    <select class="form-select" name="visibility" id="visibility" required>
                                                      <option value="0">Tier 0 Users</option>
                                                      <option value="1">Tier 1 Users</option>
                                                      <option value="2">Tier 2 Users</option>
                                                      <option value="3">Tier 3 Users</option>
                                                    </select>
                                            </div>
                                        </div>
                                        <div class="col"></div>
                                        <div class="col">
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
                                    <div class="mb-3 pb-2">
                                        <label class="form-label">Where does it take place?</label>
                                        <div class="row">
                                            <div class="col">
                                                <input type="text" class="form-control" name="city" id="city" placeholder="Enter city..." required>
                                            </div>
                                            <div class="col">
                                                <input type="text" class="form-control" name="street" id="street" placeholder="Enter Street..." required>
                                            </div>
                                            <div class="col">
                                                <input type="text" class="form-control" name="number" id="number" placeholder="Enter house number..." required>
                                            </div>

                                        </div>
                                    </div>
                                    <div>
                                        <label class="form-label">Additional Information Required</label>
                                        <div class="row pb-2">
                                            <div class="col">
                                                <!--maxParticipantsInternational-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxParticipantsInternational" id="floatingInput" min="0" required>
                                                    <label for="floatingInput">Number of Max International participants:</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <!--MaxParticipantsVolunteer-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxParticipantsVolunteer" id="floatingInput" min="0" required>
                                                    <label for="floatingInput">Number of Max Volunteer participants:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row pb-2">
                                            <div class="col">
                                                <!--eventStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventStart" id="floatingInput" required>
                                                    <label for="floatingInput">When does the event start?</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <!--eventEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="eventEnd" id="eventEnd" required>
                                                    <label for="floatingInput">When does the event end?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row pb-2">
                                            <div class="col">
                                                <!--subscriptionStart-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionStart" id="subscriptionStart" required>
                                                    <label for="floatingInput">When can the subscription start?</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <!--subscriptionEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" required>
                                                    <label for="">And when can the subscription end?</label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <!--withdrawalEnd-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" required>
                                                    <label for="">When does the withdrawal End?</label>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row pb-2">
                                            <div class="col">
                                                <!--maxWaitingList-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="number" name="maxWaitingList" id="maxWaitingList" min="0" required>
                                                    <label for="">Insert the number of the size of the waiting list:</label>

                                                </div>
                                            </div>

                                            <div class="col">
                                                <!--attributes-->
                                                <div class="form-floating mb-3">
                                                    <input class="form-control" type="text" name="attributes" id="attributes" required>
                                                    <label for="">Attributes:</label>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <!--thumbnail-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="thumbnail">Thumbnail:</label>
                                                    <input type="file" class="form-control" id="thumbnail" name="thumbnail" required>

                                                </div>
                                            </div>
                                            <div class="col">
                                                <!--poster-->
                                                <div class="mb-3">
                                                    <label class="form-label" for="poster">Poster:</label>
                                                    <input type="file" class="form-control" id="poster" name="poster" required/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col"></div>
                                        <div class="col">
                                            <button type="reset" class="btn btn-outline-primary">Reset the form</button>
                                        </div>
                                        <div class="col">
                                            <button type="submit" class="btn btn-outline-primary">Continue</button>
                                        </div>
                                        <div class="col"></div>
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