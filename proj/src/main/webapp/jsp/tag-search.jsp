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
<html>
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Tag Search</title>
</head>

<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10 col-lg-8 col-xl-6">
            <div class="card text-center border-orange">
                <h2 class="card-title bg-orange color-white p-4">Search Tag</h2>
                <div class="card-body">

                    <!--Part of-->
                    <div class="form-group mb-4">
                        <div class="d-flex justify-content-start">
                            <label for="subTag" class="mb-2 text-left">Insert (part of) Tag, empty for all tags</label>
                        </div>
                        <div class="input-container">
                            <input class="form-control" type="text" name="subTag" id="subTag" placeholder="Sub-Tag..." required>
                        </div>
                    </div>

                    <!--SUBMIT BUTTON-->
                    <div>
                        <button type="submit" id="ajaxButton" class="button bg-orange text-white border-orange px-4 py-2">Search</button>
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
