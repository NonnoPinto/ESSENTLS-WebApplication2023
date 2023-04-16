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
  <div>
    <h1>${user.name} ${user.surname}</h1>
    <hr>
    <h6>${user.dateOfBirth}</h6>
    <hr>
    <h6>${user.nationality}</h6>
    <hr>
    <p>${user.homeCountryAddress}</p>
    <hr>
    <p>${user.paduaAddress}</p>
    <hr>
    <p>${user.dietType}</p>
    <hr>
    <p>${user.allergies}</p>
  </div>

</body>
</html>
