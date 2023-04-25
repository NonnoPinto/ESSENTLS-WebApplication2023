<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Event participants</title>
</head>

<div class="navbar"><%@include file="navbar.jsp"%></div>


<div class="container">
    <div>
        <h2>${event.name}</h2>
        <hr>
        <p>Participants:</p>
        <table>
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Name
                </th>
                <th>
                    Surname
                </th>
                <th>
                    Email
                </th>
                <th>
                    Role
                </th>
                <th>
                    Date
                </th>
                <th>
                    Attributes
                </th>
            </tr>
            <c:forEach items="${participants}" var="p">
                <tr>
                    <td>${p.cardId}</td>
                    <td>${p.name}</td>
                    <td>${p.surname}</td>
                    <td>${p.email}</td>
                    <td>${p.role}</td>
                    <td>${p.date}</td>
                    <td>
                        <ul>
                            <c:forEach items="${p.attributeList}" var="att">
                                <li>${att.name}: ${att.value}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</div>

</body>
</html>