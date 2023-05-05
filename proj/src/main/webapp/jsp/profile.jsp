<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17/4/2023
  Time: 12:30 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>User details</title>
</head>
<body>
<%@include file="navbar.jsp"%>
<div>
  <div class="title">
    <h1>Profile page</h1>
  </div>
  <hr>
  <h3>${Users.name} ${Users.surname}</h3>
  <h5>Date of birth: ${Users.dateOfBirth}</h5>
  <h5>Nationality: ${Users.nationality}</h5>
  <hr>
  <p>Home country address: ${Users.homeCountryAddress}</p>
  <hr>
  <p>Italian address: ${Users.paduaAddress}</p>
  <hr>
  <p>Type of diet: ${Users.dietType}</p>
  <hr>
  <p>Allergies: ${Arrays.toString(Users.getAllergies())}</p>
</div>

<form action="<c:url value="/paymentslist"/>" method="GET">
  <div>
    <button type="submit">Show Payment List</button>
  </div>
</form>

<form action="<c:url value="/edit-profile"/>" method="GET">
  <div>
    <button type="submit">Edit Profile</button>
  </div>
</form>

<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>
