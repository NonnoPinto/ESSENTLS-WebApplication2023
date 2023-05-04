<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Search</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="containter">
  <div class="title">
    <h2>Create Cause</h2>
  </div>
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
