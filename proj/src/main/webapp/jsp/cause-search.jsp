<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 25/04/2023
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cause Search</title>
</head>
<body>
  <div class="title">
    <h1>Cause Search</h1>
    <hr>
  </div>

<div>
  <label for="causeId">Insert ID:</label>
  <input id="causeId" type="text"/><br/><br/>

  <label for="subCause">Insert (part of) Cause, empty for all causes:</label>
  <input id="subCause" type="text"/><br/><br/>

  <button type="submit" id="ajaxButton">Submit</button><br/>

</div>

<div id="results" style="margin: 2em;">

</div>

<script type="text/javascript" src="<c:url value="/js/ajax_causes.js"/>"></script>
</body>
</html>
