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

        <div>
            <p>
                <c:out value="${message.message}"/>
            </p>
        </div>

        <div class="title">
            <h2>Create Event</h2>
        </div>

        <div class="form">
            <form action="<c:url value="/create-event"/>" id="createEventForm" method="POST" enctype="multipart/form-data" >

                <!--Name-->
                <div style="padding-top: 10px;">
                    <label>
                        Event's name
                    </label>
                    <div class="input-container">
                        <input type="text" name="name" id="name" placeholder="Name.." required>
                    </div>
                </div>

                <!--Description-->
                <div style="padding-top: 10px;">
                    <label for="">Some details:</label>
                    <div class="input-container">
                        <input type="text" name="description" id="description"  style="height:200px;", placeholder="Description.." required>
                    </div>
                </div>

                <!--Price-->
                <div style="padding-top: 10px;">
                    <label for="">How much does the event cost?</label>
                    <div class="input-container">
                        <input type="number" name="price" id="price" min="0" placeholder="0" step=".01" required>
                    </div>
                </div>

                <!--Visibility-->
                <div style="padding-top: 10px;">
                    <label for="">To whom do you want to make it visible?</label>
                    <div class="input-container">
                        <select name="visibility" id="visibility" required>
                          <option value="0">Tier 0 Users</option>
                          <option value="1">Tier 1 Users</option>
                          <option value="2">Tier 2 Users</option>
                          <option value="3">Tier 3 Users</option>
                        </select>
                    </div>
                </div>

                <!--Causes-->
                <div>
                    <label for="">Which causes does the event include?</label><br>
                    <c:forEach items="${causes}" var="cause">
                        <input type="checkbox" id="${cause}" name="cs_${cause.id}" value="${cause.name}">
                        <label for="${cause.name}">${cause.name}</label><br>
                    </c:forEach>

                </div>

                <!--Location-->
                <div style="padding-top: 10px;">
                    <label for="">Where does it take place?</label>
                    <div class="input-container">
                        <input type="text" name="city" id="city" placeholder="Enter city..." required>
                        <input type="text" name="street" id="street" placeholder="Enter Street..." required>
                        <input type="text" name="number" id="number" placeholder="Enter house number..." required>
                    </div>
                </div>

                <!--maxParticipantsInternational-->
                <div style="padding-top: 10px;">
                    <label for="">Number of Max International participants:</label>
                    <div class="input-container">
                        <input type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" min="0" required>
                    </div>
                </div>

                <!--MaxParticipantsVolunteer-->
                <div style="padding-top: 10px;">
                    <label for="">Number of Max Volunteer participants:</label>
                    <div class="input-container">
                        <input type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" min="0" required>
                    </div>
                </div>

                <!--eventStart-->
                <div style="padding-top: 10px;">
                    <label for="">When does the event start?</label>
                    <div class="input-container">
                        <input type="datetime-local" name="eventStart" id="eventStart" required>
                    </div>
                </div>

                <!--eventEnd-->
                <div style="padding-top: 10px;">
                    <label for="">When does the event end?</label>
                    <div class="input-container">
                        <input type="datetime-local" name="eventEnd" id="eventEnd" required>
                    </div>
                </div>

                <!--subscriptionStart-->
                <div style="padding-top: 10px;">
                    <label for="">When can the subscription start?</label>
                    <div class="input-container">
                        <input type="datetime-local" name="subscriptionStart" id="subscriptionStart" required>
                    </div>
                </div>

                <!--subscriptionEnd-->
                <div style="padding-top: 10px;">
                    <label for="">And when can the subscription end?</label>
                    <div class="input-container">
                        <input type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" required>
                    </div>
                </div>

                <!--withdrawalEnd-->
                <div style="padding-top: 10px;">
                    <label for="">When does the withdrawal End?</label>
                    <div class="input-container">
                        <input type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" required>
                    </div>
                </div>

                <!--maxWaitingList-->
                <div style="padding-top: 10px;">
                    <label for="">Insert the number of the size of the waiting list:</label>
                    <div class="input-container">
                        <input type="number" name="maxWaitingList" id="maxWaitingList" min="0" required>
                    </div>
                </div>

                <!--attributes-->
                <div style="padding-top: 10px;">
                    <label for="">Attributes:</label>
                    <div class="input-container">
                        <input type="text" name="attributes" id="attributes" required>
                    </div>
                </div>

                <!--thumbnail-->
                <div style="padding-top: 10px;">
                    <label for="">Thumbnail:</label>
                    <div class="input-container">
                        <input type="file" name="thumbnail" id="thumbnail" required>
                    </div>
                </div>

                <!--poster-->
                <div style="padding-top: 10px;">
                    <label for="">Poster:</label>
                    <div class="input-container">
                        <input type="file" name="poster" id="poster" required/>
                    </div>
                </div>

                <div class="buttons">
                    <div>
                        <a href="">Close</a>    
                    </div>
                    <div>
                        <button type="submit">Continue</button>
                    </div>
                    <div>
                        <button type="reset">Reset the form</button>
                    </div>
                </div>


            </form>
        </div>
    </div>
    <%@include file="/html/footer.html"%>  
</body>
</html>