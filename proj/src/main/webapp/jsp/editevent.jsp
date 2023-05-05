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
    </head> 

    <body>
        <%@include file="navbar.jsp"%>

        <div class="containter">
            
            <c:choose>
                <c:when test="${event == null}">
                    <p>Event not found!</p>
                </c:when>
                <c:otherwise>
                    <div class="title">
                        <h2>Edit the event <c:out value="${event.getName()}"/></h2>
                    </div>
                    <c:out value="ID: ${event.getId().toString()}"/>
                    <div class="form">
                        <form action="" id="editEventForm" method="POST" enctype="multipart/form-data" >
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
                                    <input type="number" name="price" id="price" min="0" step=".01" value="${event.getPrice()}">
                                </div>
                            </div>

                            <!--Visibility-->
                            <div style="padding-top: 10px;">
                                <label for="">To whom do you want to make it visible?</label>
                                <div class="input-container">
                                    <select name="visibility" id="visibility" >
                                      <option value=${event.getVisibility()} selected disabled hidden>Tier ${event.getVisibility()} Users</option>
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
                                    <input type="text" name="city" id="city" value="${city}" >
                                    <input type="text" name="street" id="street" value="${street}" >
                                    <input type="text" name="number" id="number" value="${number}" >
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
                                    <input type="datetime-local" name="eventStart" id="eventStart" value="${event.getEventStart()}"> 
                                </div>
                            </div>

                            <!--eventEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Event End (currently ${event.getEventEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime-local" name="eventEnd" id="eventEnd" value="${event.getEventEnd()}">
                                </div>
                            </div>

                            <!--subscriptionStart-->
                            <div style="padding-top: 10px;">
                                <label for="">Subscription Start (currently ${event.getSubscriptionStart().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime-local" name="subscriptionStart" id="subscriptionStart" value="${event.getSubscriptionStart()}">
                                </div>
                            </div>

                            <!--subscriptionEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Subscription End (currently ${event.getSubscriptionEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime-local" name="subscriptionEnd" id="subscriptionEnd" value="${event.getSubscriptionEnd()}">
                                </div>
                            </div>

                            <!--withdrawalEnd-->
                            <div style="padding-top: 10px;">
                                <label for="">Withdrawal End (currently ${event.getWithdrawalEnd().toString()}):</label>
                                <div class="input-container">
                                    <input type="datetime-local" name="withdrawalEnd" id="withdrawalEnd" value="${event.getWithdrawalEnd()}">
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
                                <label for="">Attributes (currently ${event.getAttributes()}):</label>
                                <div class="input-container">
                                    <input type="text" name="attributes" id="attributes" value="${event.getAttributes_asString()}">
                                </div>
                            </div>

                            <!--thumbnail-->
                            <div style="padding-top: 10px;">
                                <label for="">Thumbnail:</label>
                                <c:if test="${!event.thumbnail.equals('')}">
                                    <img src="${event.thumbnail}" width="200" height="200"/>
                                </c:if>
                                <div class="input-container">
                                    <input type="file" name="thumbnail" id="thumbnail" value="${thumbnail}">
                                </div>
                            </div>

                            <!--poster-->
                            <div style="padding-top: 10px;">
                                <label for="">Poster:</label>
                                <c:if test="${!event.poster.equals('')}">
                                    <img src="${event.poster}" width="1280" height="300"/>
                                </c:if>

                                <p> Choose a new image </p>
                                <div class="input-container">
                                    <input type="file" name="poster" id="poster" value="${poster}">
                                </div>
                            </div>

                            <div class="buttons">
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
        <footer class="footer"><%@include file="/html/footer.html"%></footer>       
    </body>
</html>