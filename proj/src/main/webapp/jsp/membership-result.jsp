<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Membership Result</title>
    <%@ include file="../html/favicon.html" %>
    <%@ include file="../jsp/cdn.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10">
            <div class="card text-center border-orange">
                <h1 class="card-title bg-orange color-white p-4">Membership Submission</h1>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${user==null}">
                            <div class="my-2 mx-4">
                                <div class="pb-2">
                                    <h2>Something went wrong.</h2>
                                    <p>${message}</p>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="my-2 mx-4">
                                <div class="pb-2">
                                    <h2>Membership submitted successfully.</h2>
                                </div>
                                <div class="my-4">
                                    <p>
                                        <b>NEXT STEP: </b> Go to the office in order to sign and complete the
                                        membership!<br/>
                                        <br/>
                                        Meanwhile you can speed up the procedure by paying online if you wish.<br/>
                                        By paying online you will get a temporary card number, and you will be able to
                                        view and subscribe to events!<br/>
                                        <b>IMPORTANT: </b>Be careful that you must sign the papers in office before
                                        actually
                                        participate at events.
                                    </p>
                                    How do you want to pay for the membership?<br/>
                                    <a href="payment?action=sub">
                                        <button class="button btn bg-orange text-white border-orange px-4 py-2">ONLINE
                                        </button>
                                    </a>
                                    <a href="home">
                                        <button class="button btn bg-orange text-white border-orange px-4 py-2">CASH
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/html/footer.html" %>
</body>
</html>