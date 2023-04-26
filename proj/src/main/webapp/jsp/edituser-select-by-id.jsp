<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 17/04/2023
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Select User by ID</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<c:out value="user tier is: ${Users.getTier()}"/>

<div class="title">
  <h1>Select User by ID</h1>
  <hr>
</div>

<form method="POST" action="<c:url value="/select-user-by-id"/>">
    <label for="userId">Id:</label>
    <input id="userId" name="userId" type="text" placeholder="Insert iD.."/><br/><br/>

    <button type="submit">Submit</button><br/>
    <button type="reset">Reset the form</button>
</form>
</body>
</html>
