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
  <h1>Profile Page</h1>
  <hr>
  <h3>${user.name} ${user.surname}</h3>
  <h5>${user.dateOfBirth}</h5>
  <h5>${user.nationality}</h5>
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
