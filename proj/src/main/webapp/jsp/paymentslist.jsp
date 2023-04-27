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
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Payments list</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="title">
    <h1>Payments list</h1>
    <hr>
</div>
<table>
    <thead>
    <tr> 
        <th>User ID</th>
        <th>Payment's method</th>
        <th>Amount</th>
        <th>Note</th>
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
<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>
