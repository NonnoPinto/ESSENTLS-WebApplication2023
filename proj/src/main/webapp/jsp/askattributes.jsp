<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Event registration: additional information required</title>
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
            <h2>Additional information required</h2>
            <hr>
        </div>

        <div class="form">
            <form action="<c:url value="/confirmEvent"/>" id="createEventForm" method="POST">
                <c:forEach items="${attributes}" var="att">
                    ${att.key}: <input type="text" name="att_${fn:replace(att.key,' ','')}" value="${att.value}"><br/>
                </c:forEach>
                <input type="hidden" name="eventId" value="${event.id}">
                <input type="submit" name="submitAttr" value="Send">
            </form>
        </div>
    </div>
    <%@include file="/html/footer.html"%>
</body>
</html>