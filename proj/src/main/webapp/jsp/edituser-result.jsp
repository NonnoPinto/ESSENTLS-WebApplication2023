<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>User Edit Result</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp" %>
</head>

<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-md-10">
            <div class="card text-center border-orange">
                <h1 class="card-title bg-orange color-white p-4">Edit User Result</h1>
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
                                    <h2>User #${user.id} successfully edited.</h2>
                                    <hr>
                                </div>
                                <div class="my-4">
                                    <p class="pb-2 border-bottom border-gray">Email: ${user.email}</p>
                                    <p class="pb-2 border-bottom border-gray">Card ID: ${user.cardId}</p>
                                    <p class="pb-2 border-bottom border-gray">Tier: ${user.tier}</p>
                                    <p class="pb-2 border-bottom border-gray">Registration
                                        Date: ${user.registrationDate}</p>
                                    <p class="pb-2 border-bottom border-gray">Name: ${user.name}</p>
                                    <p class="pb-2 border-bottom border-gray">Surname: ${user.surname}</p>
                                    <p class="pb-2 border-bottom border-gray">Sex: ${user.sex}</p>
                                    <p class="pb-2 border-bottom border-gray">Date of Birth: ${user.dateOfBirth}</p>
                                    <p class="pb-2 border-bottom border-gray">Nationality: ${user.nationality}</p>
                                    <p class="pb-2 border-bottom border-gray">Home Country Address
                                        (Street: ${user.getHomeCountryAddress().getString("street")} |
                                        Number: ${user.getHomeCountryAddress().getString("number")} |
                                        City: ${user.getHomeCountryAddress().getString("city")} |
                                        ZIP: ${user.getHomeCountryAddress().getString("zip")} |
                                        Country: ${user.getHomeCountryAddress().getString("country")})</p>
                                    <p class="pb-2 border-bottom border-gray">Home Country
                                        University: ${user.homeCountryUniversity}</p>
                                    <p class="pb-2 border-bottom border-gray">Period of Stay: ${user.periodOfStay}</p>
                                    <p class="pb-2 border-bottom border-gray">Phone Number: ${user.phoneNumber}</p>
                                    <p class="pb-2 border-bottom border-gray">Padua Address
                                        (Street: ${user.getPaduaAddress().getString("street")} |
                                        Number: ${user.getPaduaAddress().getString("number")} |
                                        City: ${user.getPaduaAddress().getString("city")} |
                                        ZIP: ${user.getPaduaAddress().getString("zip")} |
                                        Country: ${user.getPaduaAddress().getString("country")})</p>
                                    <p class="pb-2 border-bottom border-gray">Document Type: ${user.documentType}</p>
                                    <p class="pb-2 border-bottom border-gray">Document
                                        Number: ${user.documentNumber}</p>
                                    <p class="pb-2 border-bottom border-gray">Diet Type: ${user.dietType}</p>
                                    <p class="pb-2 border-bottom border-gray">
                                        Allergies: ${Arrays.toString(user.getAllergies())}</p>
                                    <p class="pb-2 border-bottom border-gray">Email
                                        Confirmed: ${user.emailConfirmed}</p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="row d-flex my-4">
                        <a href="<c:url value="/search-users"/>" id="back-button">
                            <button type="button" class="button btn bg-orange border-orange color-white px-4 py-2">Back</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/html/footer.html"%>
</body>
</html>