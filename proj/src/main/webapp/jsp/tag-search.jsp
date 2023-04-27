<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 25/04/2023
  Time: 00:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tag Search</title>
</head>
<body>
    <div class="title">
        <h1>Tag Search</h1>
        <hr>
    </div>

    <div>
        <label for="subTag">Insert (part of) Tag, empty for all tags:</label>
        <input id="subTag" type="text" name="subTag"/><br/><br/>

        <button type="submit" id="ajaxButton">Submit</button><br/>

    </div>

    <div id="results" style="margin: 2em;">

    </div>

    <script type="text/javascript" src="<c:url value="/js/ajax_tags.js"/>"></script>
</body>
</html>
