<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Event participants</title>
        <%@ include file="../html/favicon.html"%>
        <%@ include file="../jsp/cdn.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>

        <div class="container">

            <h1 class="page-title text-center p-2">Event participants</h1>
            
            <div class="event-title">
                <h2>${event.name}</h2><hr>
            </div>
            
            <div class="row justify-content-center my-4">
                <h3 class="py-2">Participants:</h3>
                <div class="col-12">
                    <div class="card text-center border-cyan">
                        <div class="table-responsive">
                            <table class="table table-hover mh-75 align-middle">
                                <thead>
                                    <tr>
                                        <th class="py-3 px-4" scope="col">ID</th>
                                        <th class="py-3 px-3" scope="col">Name</th>
                                        <th class="py-3" scope="col">Surname</th>
                                        <th class="py-3 px-4" scope="col">Email</th>
                                        <th class="py-3 px-4" scope="col">Role</th>
                                        <th class="py-3 px-4" scope="col">Date</th>
                                        <th class="py-3 px-4" scope="col">Attributes</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${participants}" var="p">
                                        <tr>
                                            <td class="px-2">${p.cardId}</td>
                                            <td>${p.name}</td>
                                            <td>${p.surname}</td>
                                            <td>${p.email}</td>
                                            <td>${p.role}</td>
                                            <td>${p.date}</td>
                                            <td>
                                                <ul>
                                                    <c:forEach items="${p.attributeMap}" var="att">
                                                        <li>${att.key}: ${att.value}</li>
                                                    </c:forEach>
                                                </ul>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>