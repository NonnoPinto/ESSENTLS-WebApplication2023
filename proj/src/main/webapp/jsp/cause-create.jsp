<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Create</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<div class="containter">
  <label for="causeName">Insert Cause Name:</label>
  <input id="causeName" name="causeName" type="text"/><br/><br/>

  <button type="submit" id="ajaxButton">Submit</button><br/>
  <div>
    <h3>Here the causes already present!</h3><br>
    <c:forEach items="${causes}" var="cause">
        <p>${cause.name}</p><br>
    </c:forEach>

</div>

</div>

<div id="results" style="margin: 2em;">

</div>

<script type="text/javascript" src="<c:url value="/js/ajax_cause_create.js"/>"></script>
</body>
</html>
