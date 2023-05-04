<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="ISO-8859-1">
    <title>Edit Profile</title>
</head>
<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="title">
    <h2>Edit Profile</h2>
    <hr>
</div>

<form action="<c:url value="/edit-profile"/>" method="POST">
    <div>
        <button type="submit">Show Payment List</button>
    </div>
</form>



<c:choose>
    <c:when test="${user == null}">
        <p>User not found</p>
    </c:when>
    <c:otherwise>
        <form method="POST" action="<c:url value="/edit-profile"/>">
            <input type="hidden" name="userId" value="${user.getId()}"/>

            <label for="userName">Name (currently ${user.getName()}):</label>
            <input id="userName" value="${user.getName()}" name="userName" type="text"/><br/><br/>

            <label for="userSurname">Surname (currently ${user.getSurname()}):</label>
            <input id="userSurname" value="${user.getSurname()}" name="userSurname" type="text"/><br/><br/>

            <label for="userSex">Sex (currently ${user.getSex()}):</label>
            <select id="userSex" name="userSex">
                <option value="${user.getSex()}" selected hidden>${user.getSex()}</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="others">Others</option>
            </select><br><br>

            <label for="userDateOfBirth">Date of Birth (currently ${user.getDateOfBirth().toString()}):</label>
            <input id="userDateOfBirth" value="${user.getDateOfBirth()}" name="userDateOfBirth" type="date"/><br/><br/>

            <label for="userNationality">Nationality (currently ${user.getNationality()}):</label>
            <input id="userNationality" value="${user.getNationality()}" name="userNationality" type="text"/><br/><br/>

            <label for="userHomeCountryAddress">Home Country Address (currently Street: ${user.getHomeCountryAddress().getString("street")} | Number: ${user.getHomeCountryAddress().getString("number")} | City: ${user.getHomeCountryAddress().getString("city")} | ZIP: ${user.getHomeCountryAddress().getString("zip")} | Country: ${user.getHomeCountryAddress().getString("country")}):</label>
            <div class="input-container" id="userHomeCountryAddress">
                <input type="text" name="userHomeCountryAddress-street" id="userHomeCountryAddress-street" value="${user.getHomeCountryAddress().getString("street")}">
                <input type="text" name="userHomeCountryAddress-number" id="userHomeCountryAddress-number" value="${user.getHomeCountryAddress().getString("number")}">
                <input type="text" name="userHomeCountryAddress-city" id="userHomeCountryAddress-city" value="${user.getHomeCountryAddress().getString("city")}">
                <input type="text" name="userHomeCountryAddress-zip" id="userHomeCountryAddress-zip" value="${user.getHomeCountryAddress().getString("zip")}">
                <input type="text" name="userHomeCountryAddress-country" id="userHomeCountryAddress-country" value="${user.getHomeCountryAddress().getString("country")}">
            </div><br>

            <label for="userHomeCountryUniversity">Home Country University (currently ${user.getHomeCountryUniversity()}):</label>
            <input id="userHomeCountryUniversity" value="${user.getHomeCountryUniversity()}" name="userHomeCountryUniversity" type="text"/><br/><br/>

            <label for="userPeriodOfStay">Period of Stay (currently ${user.getPeriodOfStay()}):</label>
            <select id="userPeriodOfStay" name="userPeriodOfStay">
                <option value="${user.getPeriodOfStay().toString()}" selected hidden>${user.getPeriodOfStay().toString()}</option>
                <option value="1">1 (one semester)</option>
                <option value="2">2 (both semesters)</option>
            </select><br><br>

            <label for="userPhoneNumber">Phone Number (currently ${user.getPhoneNumber()}):</label>
            <input id="userPhoneNumber" value="${user.getPhoneNumber()}" name="userPhoneNumber" type="text"/><br/><br/>

            <label for="userPaduaAddress">Padua Address (currently Street: ${user.getPaduaAddress().getString("street")} | Number: ${user.getPaduaAddress().getString("number")} | City: ${user.getPaduaAddress().getString("city")} | ZIP: ${user.getPaduaAddress().getString("zip")} | Country: ${user.getPaduaAddress().getString("country")}):</label>
            <div class="input-container" id="userPaduaAddress">
                <input type="text" name="userPaduaAddress-street" id="userPaduaAddress-street" value="${user.getPaduaAddress().getString("street")}">
                <input type="text" name="userPaduaAddress-number" id="userPaduaAddress-number" value="${user.getPaduaAddress().getString("number")}">
                <input type="text" name="userPaduaAddress-city" id="userPaduaAddress-city" value="${user.getPaduaAddress().getString("city")}">
                <input type="text" name="userPaduaAddress-zip" id="userPaduaAddress-zip" value="${user.getPaduaAddress().getString("zip")}">
                <input type="text" name="userPaduaAddress-country" id="userPaduaAddress-country" value="${user.getPaduaAddress().getString("country")}">
            </div><br>

            <label for="userDietType">Diet (currently ${user.getDietType()}):</label>
            <select id="userDietType" name="userDietType">
                <option value="${user.getDietType()}" selected hidden>${user.getDietType()}</option>
                <option value="No specific">No specific</option>
                <option value="Vegetarian">Vegetarian</option>
                <option value="Vegan">Vegan</option>
                <option value="Halal">Halal</option>
                <option value="Kosher">Kosher</option>
                <option value="Pescatarian">Pescatarian</option>
            </select><br><br>

            <label for="userAllergies">Allergies (currently ${Arrays.toString(user.getAllergies())}. Separate by comma):</label>
            <input id="userAllergies" value="${Arrays.toString(user.getAllergies()).replaceAll("[^a-zA-Z\\p{Zs},]", "")}" name="userAllergies" type="text" style="width:1000px;"/><br/><br/>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
    </c:otherwise>
</c:choose>








</body>
</html>
