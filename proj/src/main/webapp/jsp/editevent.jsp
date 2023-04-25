<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Edit Event</title>
        <!-- js and css -->
        <!--<script src="${pageContext.request.contextPath}/js/login.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>-->
    </head> 

    <body>
        <div class="navbar"><%@include file="navbar.jsp"%></div>

        <div class="containter">
            <div class="title">
                <h2>Edit the event <c:out value="${event.getName()}"/></h2>
            </div>

            <c:choose>
                <c:when test="${event == null}">
                    <p>Event not found!</p>
                </c:when>
                <c:otherwise>
                    <c:out value="ID: ${event.getId().toString()}"/>
                    <div class="form">
                        <form action="<c:url value="/editEvent/"/>" id="editEventForm" method="POST">
                            <!--Name-->
                            <div style="padding-top: 10px;">
                                <label for="">Name (currently ${event.getName()}):</label>
                                <div class="input-container">
                                    <input type="text" name="name" id="name" value="${event.getName()}">
                                </div>
                            </div>
        
                            <!--Description-->
                            <div style="padding-top: 10px;">
                                <label for="">Description (currently ${event.getDescription()}):</label>
                                <div class="input-container">
                                    <input type="text" name="description" id="description" value="${event.getDescription()}" style="height:200px;">
                                </div>
                            </div>

                            <!--Price-->
                            <div style="padding-top: 10px;">
                                <label for="">Price (currently ${event.getPrice()}):</label>
                                <div class="input-container">
                                    <input type="number" name="price" id="price" min="0" value="${event.getPrice()}">
                                </div>
                            </div>

                            <!--Visibility-->
                            <div style="padding-top: 10px;">
                                <label for="">Visibility:</label>
                                <div class="input-container">
                                    <input type="radio" id="visible" name="visibility" value="visible">
                                    <label for="visible">Visible</label><br>
                                    <input type="radio" id="hidden" name="visibility" value="Hidden">
                                    <label for="hidden">Hidden</label><br>
                                </div>
                            </div>

                            <!--Location-->
                            <div style="padding-top: 10px;">
                                <label for="">Location (currently ${event.getLocation()}):</label>
                                <div class="input-container">
                                    <input type="text" name="location" id="location" value="${event.getLocation()}">
                                </div>
                            </div>

                            <!--maxParticipantsInternational-->
                            <div style="padding-top: 10px;">
                                <label for="">Max participants International (currently ${event.getMaxParticipantsInternational()}):</label>
                                <div class="input-container">
                                    <input type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" min="0" value="${event.getMaxParticipantsInternational()}">
                                </div>
                            </div>

                            <!--MaxParticipantsVolunteer-->
                            <div style="padding-top: 10px;">
                                <label for="">Max participants Volunteer (currently ${event.getMaxParticipantsVolunteer()}):</label>
                                <div class="input-container">
                                    <input type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" min="0" value="${event.getMaxParticipantsVolunteer()}">
                                </div>
                            </div>

                            <!--eventStart-->
                            <div style="padding-top: 10px;">
                                <label for="">Event Start (currently ${event.getEventStart().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime" name="eventStart" id="eventStart" value="${event.getEventStart()}"> <!--value could not work, check type-->
                                </div>
                            </div>

                            <!--eventEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Event End (currently ${event.getEventEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime" name="eventEnd" id="eventEnd" value="${event.getEventEnd()}"><!--value could not work, check type-->
                                </div>
                            </div>

                            <!--subscriptionStart-->
                            <div style="padding-top: 10px;">
                                <label for="">Subscription Start (currently ${event.getSubscriptionStart().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime" name="subscriptionStart" id="subscriptionStart" value="${event.getSubscriptionStart()}"><!--value could not work, check type-->
                                </div>
                            </div>

                            <!--subscriptionEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Subscription End (currently ${event.getSubscriptionEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime" name="subscriptionEnd" id="subscriptionEnd" value="${event.getSubscriptionEnd()}"><!--value could not work, check type-->
                                </div>
                            </div>

                            <!--withdrawalEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Withdrawal End (currently ${event.getWithdrawalEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime" name="withdrawalEnd" id="withdrawalEnd" value="${event.getWithdrawalEnd()}"><!--value could not work, check type-->
                                </div>
                            </div>

                            <!--maxWaitingList-->
                            <div style="padding-top: 10px;">
                                <label for="">Max waiting list (currently ${event.getMaxWaitingList()}):</label>
                                <div class="input-container">
                                    <input type="number" name="maxWaitingList" id="maxWaitingList" min="0" value="${event.getMaxWaitingList()}">
                                </div>
                            </div>

                            <!--attributes-->
                            <div style="padding-top: 10px;">
                                <label for="">Notes (currently ${event.getAttributes()}):</label>
                                <div class="input-container">
                                    <input type="text" name="attributes" id="attributes" value="${event.getAttributes()}">
                                </div>
                            </div>

                            <!--thumbnail-->
                            <div style="padding-top: 10px;">
                                <label for="">Thumbnail (currently ${event.getThumbnail()}):</label>
                                <div class="input-container">
                                    <input type="text" name="thumbnail" id="thumbnail" value="${event.getThumbnail()}">
                                </div>
                            </div>

                            <!--poster-->
                            <div style="padding-top: 10px;">
                                <label for="">Poster (currently ${event.getPoster()}):</label>
                                <div class="input-container">
                                    <input type="text" name="poster" id="poster" value="${event.getPoster()}">
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
                </c:otherwise>
            </c:choose>
        <footer class="footer"><%-- @ include file="/html/footer.html" --%></footer>       
    </body>
</html>