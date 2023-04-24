<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17/4/2023
  Time: 12:30 am
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>User detail</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div>
  <h1>Profile Page</h1>
  <hr>
  <h3>${Users.name} ${Users.surname}</h3>
  <h5>${Users.dateOfBirth}</h5>
  <h5>${Users.nationality}</h5>
  <hr>
  <p>${Users.homeCountryAddress}</p>
  <hr>
  <p>${Users.paduaAddress}</p>
  <hr>
  <p>${Users.dietType}</p>
  <hr>
  <p>${Users.allergies}</p>
</div>

<form action="<c:url value="/paymentslist"/>" method="POST">
  <div>
    <button type="submit">Show Payment List</button>
  </div>
</form>

</body>
</html>
