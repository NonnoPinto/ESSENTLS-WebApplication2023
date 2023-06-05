<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Select User by ID</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp" %>
</head>

<body>
<%@include file="navbar.jsp" %>

<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10 col-lg-8 col-xl-6">
            <div class="card text-center border-blue">
                <h1 class="card-title bg-blue color-white p-4">Select User by ID</h1>
                <div class="card-body">
                <form method="POST" action="<c:url value="/select-user-by-id"/>">
                    <div class="form-group mb-4">
                        <div class="d-flex justify-content-start">
                            <label for="userId" class="mb-2 text-left">Id:</label>
                        </div>
                        <div class="input-container">
                            <input class="form-control" id="userId" name="userId" type="number" min="0" placeholder="Insert ID.." required/><br/><br/>
                        </div>
                    </div>

                    <button type="submit" class="button btn bg-blue text-white border-blue px-4 py-2">Submit</button>
                </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>
