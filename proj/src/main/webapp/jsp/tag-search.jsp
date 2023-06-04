<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Tag Search</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp"%>
</head>

<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10 col-lg-8 col-xl-6">
            <div class="card text-center border-cyan">
                <h1 class="card-title bg-cyan color-white p-4">Search Tag</h1>
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
    <noscript>
        <div class="row justify-content-center my-4">
            <div class="justify-content-center mt-2 col-md-10 col-lg-8 col-xl-6" id="js-alert" >
                <div class="alert alert-warning text-center" role="alert">
                    javascript disabled, please enable it for a better experience.
                </div>
            </div>
        </div>
    </noscript>
</div>


<script type="text/javascript" src="<c:url value="/js/ajax_tags.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/enter_submit.js"/>"></script>
<%@include file="/html/footer.html"%>
</body>
</html>
