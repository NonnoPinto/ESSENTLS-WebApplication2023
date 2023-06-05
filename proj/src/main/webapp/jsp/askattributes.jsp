<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Event registration: additional information required</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp"%>
</head>

<body>
<%@include file="navbar.jsp"%>


    <div class="container">
        <div class="row justify-content-center my-4">
            <div class="col-xxl-10 col-12">
                <div class="card text-center border-cyan">
                    <div class="bg-cyan text-white">
                        <h1 class="page-title text-center p-2">Additional information required</h1>
                    </div>
                    <div class="card-body text-center">
                        <div>
                            <p>
                                <c:out value="${message.message}"/>
                            </p>
                        </div>

                        <div class="form" id="attributes_form">
                            <form action="<c:url value="/confirmEvent"/>" id="createEventForm" method="POST">
                                <c:forEach items="${attributes}" var="att">
                                    <label for="${fn:replace(att.key,' ','')}" class="form-label">${att.key}</label>
                                    <input type="text" id="${fn:replace(att.key,' ','')}" class="form-control" name="att_${fn:replace(att.key,' ','')}" value="${att.value}" maxlength="255"><br/>
                                </c:forEach>
                                <input type="hidden" name="eventId" value="${event.id}">
                                <input type="submit" name="submitAttr" class="button btn btn-primary bg-cyan border-cyan px-4 py-2" value="Send">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/html/footer.html"%>
</body>
</html>