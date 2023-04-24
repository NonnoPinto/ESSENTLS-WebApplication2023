<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19/4/2023
  Time: 12:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html><html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Payments List</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<h1>Payments List</h1>
<table>
    <thead>
    <tr>
        <th>UserId</th>
        <th>method</th>
        <th>amount</th>
        <th>note</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="payment" items="${Payments}">
        <tr>
            <td><c:out value="${payment.getUserId()}"/></td>
            <td><c:out value="${payment.getMethod()}"/></td>
            <td><c:out value="${payment.getAmount()}"/></td>
            <td><c:out value="${payment.getNotes()}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
