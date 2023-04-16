<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Create Event</title>
    </head> 

    <body>

        <div class="containter">
            <div class="title">
                <h2>Create Event</h2>
            </div>

            <div class="form">
                <form action="<c:url value="/createEvent/"/>" id="createEventForm" method="POST">

                    <!--Name-->
                    <div style="padding-top: 10px;">
                        <label for="">Name</label>
                        <div class="inputContainer">
                            <input type="text" name="name" id="name">
                        </div>
                    </div>
 
                    <!--Description-->
                    <div style="padding-top: 10px;">
                        <label for="">Description</label>
                        <div class="inputContainer">
                            <input type="text" name="description" id="description"  style="height:200px;">
                        </div>
                    </div>

                    <!--Price-->
                    <div style="padding-top: 10px;">
                        <label for="">Price</label>
                        <div class="inputContainer">
                            <input type="number" name="price" id="price" min="0" >
                        </div>
                    </div>

                    <!--Visibility-->
                    <div style="padding-top: 10px;">
                        <label for="">Visibility</label>
                        <div class="inputContainer">
                            <input type="radio" id="visible" name="visibility" value="visible">
                            <label for="visible">Visible</label><br>
                            <input type="radio" id="hidden" name="visibility" value="Hidden">
                            <label for="hidden">Hidden</label><br>
                        </div>
                    </div>

                    <!--Location-->
                    <div style="padding-top: 10px;">
                        <label for="">Location</label>
                        <div class="inputContainer">
                            <input type="text" name="location" id="location" >
                        </div>
                    </div>

                    <!--maxParticipantsInternational-->
                    <div style="padding-top: 10px;">
                        <label for="">Max participants International</label>
                        <div class="inputContainer">
                            <input type="number" name="maxParticipantsInternational" id="maxParticipantsInternational" min="0" >
                        </div>
                    </div>

                    <!--MaxParticipantsVolunteer-->
                    <div style="padding-top: 10px;">
                        <label for="">Max participants Volunteer</label>
                        <div class="inputContainer">
                            <input type="number" name="maxParticipantsVolunteer" id="maxParticipantsVolunteer" min="0" >
                        </div>
                    </div>

                    <!--eventStart-->
                    <div style="padding-top: 10px;">
                        <label for="">Event Start</label>
                        <div class="inputContainer">
                            <input type="datetime" name="eventStart" id="eventStart" >  
                        </div>
                    </div>

                    <!--eventEnd-->
                    <div style="padding-top: 10px;">
                        <label for="">Event End</label>
                        <div class="inputContainer">
                            <input type="datetime" name="eventEnd" id="eventEnd" > 
                        </div>
                    </div>

                    <!--subscriptionStart-->
                    <div style="padding-top: 10px;">
                        <label for="">Subscription Start</label>
                        <div class="inputContainer">
                            <input type="datetime" name="subscriptionStart" id="subscriptionStart" >
                        </div>
                    </div>

                    <!--subscriptionEnd-->
                    <div style="padding-top: 10px;">
                        <label for="">Subscription End</label>
                        <div class="inputContainer">
                            <input type="datetime" name="subscriptionEnd" id="subscriptionEnd" >
                        </div>
                    </div>

                    <!--withdrawalEnd-->
                    <div style="padding-top: 10px;">
                        <label for="">Withdrawal End</label>
                        <div class="inputContainer">
                            <input type="datetime" name="withdrawalEnd" id="withdrawalEnd"> 
                        </div>
                    </div>

                    <!--maxWaitingList-->
                    <div style="padding-top: 10px;">
                        <label for="">Max waiting list</label>
                        <div class="inputContainer">
                            <input type="number" name="maxWaitingList" id="maxWaitingList" min="0">
                        </div>
                    </div>

                    <!--attributes-->
                    <div style="padding-top: 10px;">
                        <label for="">Notes</label>
                        <div class="inputContainer">
                            <input type="text" name="attributes" id="attributes">
                        </div>
                    </div>

                    <!--thumbnail-->
                    <div style="padding-top: 10px;">
                        <label for="">Thumbnail</label>
                        <div class="inputContainer">
                            <input type="text" name="thumbnail" id="thumbnail" >
                        </div>
                    </div>

                    <!--poster-->
                    <div style="padding-top: 10px;">
                        <label for="">Poster</label>
                        <div class="inputContainer">
                            <input type="text" name="poster" id="poster">
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
                    
                    
                    <c:choose>
                        <c:when test="${message.isError()}">
                            <div>
                                <div role="alert">
                                        <span>
                                            <c:out value="${message.message}"/>
                                        </span>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>

                </form>
            </div>
        </div>
        <footer class="footer"><%-- @ include file="/html/footer.html" --%></footer>       
    </body>
</html>