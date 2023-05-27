<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Edit Profile</title>
        <%@ include file="../html/favicon.html"%>
    </head>
    <body>
    <%@include file="navbar.jsp"%>
        <div class="container">
            <div class="row justify-content-center my-4">
                <div class="col-md-10">
                    <div class="card text-center border-orange">
                        <h2 class="card-title bg-orange color-white p-4">Edit Profile</h2>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${user == null}">
                                    <p>User not found</p>
                                </c:when>
                                <c:otherwise>
                                    <form method="POST" action="<c:url value="/edit-profile"/>">
                                        <input type="hidden" name="userId" value="${user.getId()}"/>

                                            <%--Name--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userName" class="mb-2 text-left">Name (currently ${user.getName()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userName" value="${user.getName()}" name="userName" maxlength="50" type="text" class="form-control"/>
                                            </div>
                                        </div>
                                            <%--Surname--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userSurname" class="mb-2 text-left">Surname (currently ${user.getSurname()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userSurname" value="${user.getSurname()}" name="userSurname" maxlength="50" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--SEX--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userSex" class="mb-2 text-left">Sex (currently ${user.getSex()}):</label>
                                            </div>
                                            <div>
                                                <select id="userSex" name="userSex" class="form-select">
                                                    <option value="${user.getSex()}" selected hidden>${user.getSex()}</option>
                                                    <option value="male">male</option>
                                                    <option value="female">female</option>
                                                    <option value="others">others</option>
                                                </select>
                                            </div>
                                        </div>

                                            <%--Date of Birth--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userDateOfBirth" class="mb-2 text-left">Date of Birth (currently ${user.getDateOfBirth().toString()}):</label>
                                            </div>
                                            <div>
                                                <input id="userDateOfBirth" value="${user.getDateOfBirth()}" name="userDateOfBirth" type="date" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Nationality--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userNationality" class="mb-2 text-left">Nationality (currently ${user.getNationality()}):</label>
                                            </div>
                                            <div>
                                                <input id="userNationality" value="${user.getNationality()}" name="userNationality" type="text" maxlength="100" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Home Country Address--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userHomeCountryAddress" class="mb-2 text-left">Home Country Address (currently Street: ${user.getHomeCountryAddress().getString("street")} | Number: ${user.getHomeCountryAddress().getString("number")} | City: ${user.getHomeCountryAddress().getString("city")} | ZIP: ${user.getHomeCountryAddress().getString("zip")} | Country: ${user.getHomeCountryAddress().getString("country")}):</label>
                                            </div>
                                            <div class="row input-container" id="userHomeCountryAddress">
                                                <div class="col">
                                                    <label for="userHomeCountryAddress-street" class="form-label">Street</label>
                                                    <input type="text" name="userHomeCountryAddress-street" id="userHomeCountryAddress-street" maxlength="100" class="form-control" value="${user.getHomeCountryAddress().getString("street")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userHomeCountryAddress-number" class="form-label">Number</label>
                                                    <input type="text" name="userHomeCountryAddress-number" id="userHomeCountryAddress-number" maxlength="5" class="form-control" value="${user.getHomeCountryAddress().getString("number")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userHomeCountryAddress-city" class="form-label">City</label>
                                                    <input type="text" name="userHomeCountryAddress-city" id="userHomeCountryAddress-city" maxlength="60" class="form-control" value="${user.getHomeCountryAddress().getString("city")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userHomeCountryAddress-zip" class="form-label">ZIP</label>
                                                    <input type="text" name="userHomeCountryAddress-zip" id="userHomeCountryAddress-zip" maxlength="5" class="form-control" value="${user.getHomeCountryAddress().getString("zip")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userHomeCountryAddress-country" class="form-label">Country</label>
                                                    <input type="text" name="userHomeCountryAddress-country" id="userHomeCountryAddress-country" maxlength="55" class="form-control" value="${user.getHomeCountryAddress().getString("country")}">
                                                </div>
                                            </div>
                                        </div>

                                            <%--Home Country University--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userHomeCountryUniversity" class="mb-2 text-left">Home Country University (currently ${user.getHomeCountryUniversity()}):</label>
                                            </div>
                                            <div>
                                                <input id="userHomeCountryUniversity" maxlength="150" value="${user.getHomeCountryUniversity()}" name="userHomeCountryUniversity" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Period of Stay--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userPeriodOfStay" class="mb-2 text-left">Period of Stay (currently ${user.getPeriodOfStay()}):</label>
                                            </div>
                                            <div>
                                                <select id="userPeriodOfStay" name="userPeriodOfStay" class="form-select">
                                                    <option value="${user.getPeriodOfStay().toString()}" selected hidden>${user.getPeriodOfStay().toString()}</option>
                                                    <option value="1">1 (one semester)</option>
                                                    <option value="2">2 (both semesters)</option>
                                                </select>
                                            </div>
                                        </div>

                                            <%--Phone Number--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userPhoneNumber" class="mb-2 text-left">Phone Number (currently ${user.getPhoneNumber()}):</label>
                                            </div>
                                            <div>
                                                <input id="userPhoneNumber" value="${user.getPhoneNumber()}" maxlength="50" name="userPhoneNumber" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Padua Address--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userPaduaAddress" class="mb-2 text-left">Padua Address (currently Street: ${user.getPaduaAddress().getString("street")} | Number: ${user.getPaduaAddress().getString("number")} | City: ${user.getPaduaAddress().getString("city")} | ZIP: ${user.getPaduaAddress().getString("zip")} | Country: ${user.getPaduaAddress().getString("country")}):</label>
                                            </div>
                                            <div class="row input-container" id="userPaduaAddress">
                                                <div class="col">
                                                    <label for="userPaduaAddress-street">Street</label>
                                                    <input type="text" name="userPaduaAddress-street" maxlength="35" maxlength="10" id="userPaduaAddress-street" class="form-control" value="${user.getPaduaAddress().getString("street")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userPaduaAddress-number">Number</label>
                                                    <input type="text" name="userPaduaAddress-number" maxlength="5" id="userPaduaAddress-number" class="form-control" value="${user.getPaduaAddress().getString("number")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userPaduaAddress-city">City</label>
                                                    <input type="text" name="userPaduaAddress-city" maxlength="25" id="userPaduaAddress-city" class="form-control" value="${user.getPaduaAddress().getString("city")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userPaduaAddress-zip">ZIP</label>
                                                    <input type="text" name="userPaduaAddress-zip" maxlength="5" id="userPaduaAddress-zip" class="form-control" value="${user.getPaduaAddress().getString("zip")}">
                                                </div>
                                                <div class="col">
                                                    <label for="userPaduaAddress-country">Country</label>
                                                    <input type="text" name="userPaduaAddress-country" maxlength="55" id="userPaduaAddress-country" class="form-control" value="${user.getPaduaAddress().getString("country")}">
                                                </div>
                                            </div>
                                        </div>

                                            <%--Diet Type--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userDietType" class="mb-2 text-left">Diet (currently ${user.getDietType()}):</label>
                                            </div>
                                            <div>
                                                <select id="userDietType" name="userDietType" class="form-select">
                                                    <option value="${user.getDietType()}" selected hidden>${user.getDietType()}</option>
                                                    <option value="No specific">No specific</option>
                                                    <option value="Vegetarian">Vegetarian</option>
                                                    <option value="Vegan">Vegan</option>
                                                    <option value="Halal">Halal</option>
                                                    <option value="Kosher">Kosher</option>
                                                    <option value="Pescatarian">Pescatarian</option>
                                                </select>
                                            </div>
                                        </div>


                                            <%--Allergies--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userAllergies" class="mb-2 text-left">Allergies (currently ${Arrays.toString(user.getAllergies())}. Separate by comma):</label>
                                            </div>
                                            <div>
                                                <input id="userAllergies" value="${Arrays.toString(user.getAllergies()).replaceAll("[^a-zA-Z\\p{Zs},]", "")}" maxlength="255" name="userAllergies" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Button--%>
                                        <div class="row d-flex my-4">
                                            <div>
                                                <button type="submit" class="button bg-orange text-white border-orange px-4 py-2">Submit</button>
                                            </div>
                                        </div>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <%@include file="/html/footer.html"%>
    </body>
</html>
