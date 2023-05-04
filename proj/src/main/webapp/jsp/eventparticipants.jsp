<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Event participants</title>
</head>

<div class="navbar"><%@include file="navbar.jsp"%></div>

<div>
    <h1>Event participants</h1>
    <hr>
</div>
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
                            <c:forEach items="${p.attributeMap}" var="att">
                                <li>${att.key}: ${att.value}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</div>
<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>