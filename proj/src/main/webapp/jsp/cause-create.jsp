<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 26/04/2023
  Time: 01:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cause Create</title>
</head>
<body>
  <div class="title">
    <h1>Cause Create</h1>
    <hr>
  </div>

<div>
  <label for="causeName">Insert Cause Name:</label>
  <input id="causeName" type="text"/><br/><br/>

  <button type="submit" id="ajaxButton">Submit</button><br/>

</div>

<div id="results" style="margin: 2em;">

</div>

<script type="text/javascript" src="<c:url value="/js/ajax_cause_create.js"/>"></script>
</body>
</html>
