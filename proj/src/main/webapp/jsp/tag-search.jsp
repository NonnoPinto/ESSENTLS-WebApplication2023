<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 25/04/2023
  Time: 00:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Tag Search</title>
    <%@ include file="../html/favicon.html"%>
</head>

<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10 col-lg-8 col-xl-6">
            <div class="card text-center border-cyan">
                <h2 class="card-title bg-cyan color-white p-4">Search Tag</h2>
                <div class="card-body">

                    <!--Part of-->
                    <div class="form-group mb-4">
                        <div class="d-flex justify-content-start">
                            <label for="subTag" class="mb-2 text-left">Insert (part of) Tag, empty for all tags</label>
                        </div>
                        <div class="input-container">
                            <input class="form-control" maxlength="255" type="text" name="subTag" id="subTag" placeholder="Sub-Tag..." required>
                        </div>
                    </div>

                    <!--SUBMIT BUTTON-->
                    <div class="my-2">
                        <button type="submit" id="ajaxButton" class="button bg-cyan text-white border-cyan px-4 py-2">Search</button>
                    </div>
                    <div class="my-2">
                        <button type="submit" id="ajaxButtonAll" class="button bg-white border-cyan color-cyan px-4 py-2">Show all Tags</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="row justify-content-center my-4">
        <div id="results">
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/js/ajax_tags.js"/>"></script>
<%@include file="/html/footer.html"%>
</body>
</html>
