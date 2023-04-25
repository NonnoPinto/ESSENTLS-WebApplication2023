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
    <title>Tag Search</title>
</head>
<body>
    <h1>Tag Search</h1>

    <div>
        <label for="subTag">Insert (part of) Tag, empty for all tags:</label>
        <input id="subTag" type="text"/><br/><br/>

        <button type="submit" id="ajaxButton">Submit</button><br/>

    </div>

    <div id="results" style="margin: 2em;">

    </div>

    <script type="text/javascript" src="<c:url value="/js/ajax_tags.js"/>"></script>
</body>
</html>
