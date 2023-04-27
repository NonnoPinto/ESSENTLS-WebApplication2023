
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <title>Search User</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<div class="title">
  <h1>Search User</h1>
  <hr>
</div>

<form method="POST" action="<c:url value="/search-users"/>">
  <label for="userId">Id:</label>
  <input id="userId" name="userId" type="text"/><br/><br/>
  <label for="userName">Name:</label>
  <input id="userName" name="userName" type="text"/><br/><br/>
  <label for="userSurname">Surname:</label>
  <input id="userSurname" name="userSurname" type="text"/><br/><br/>
  <label for="userCardId">CardId:</label>
  <input id="userCardId" name="userCardId" type="text"/><br/><br/>
  <label for="userEmail">Email:</label>
  <input id="userEmail" name="userEmail" type="text"/><br/><br/>

  <button type="submit">Submit</button><br/>
  <button type="reset">Reset the form</button>
</form>
<footer class="footer"><%@include file="/html/footer.html"%></footer>
</body>
</html>
