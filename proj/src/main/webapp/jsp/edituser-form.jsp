<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 17/04/2023
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit User</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
    
<h1>Edit User</h1>

<c:choose>
    <c:when test="${user == null}">
        <p>User not found</p>
    </c:when>
    <c:otherwise>
        <c:out value="ID: ${user.getId().toString()}"/><br><br>
        <form method="POST" action="<c:url value="/edit-user"/>">
            <input type="hidden" name="userId" value="${user.getId()}"/>

            <label for="userEmail">Email (currently ${user.getEmail()}):</label>
            <input id="userEmail" value="${user.getEmail()}" name="userEmail" type="text"/><br/><br/>

            <input type="hidden" name="userPassword" value="${user.getPassword()}"/>

            <label for="userCardId">CardId (currently ${user.getCardId()}):</label>
            <input id="userCardId" value="${user.getCardId()}" name="userCardId" type="text"/><br/><br/>

            <label for="userTier">Tier (currently ${user.getTier()}):</label>
            <input id="userTier" value="${user.getTier()}" name="userTier" type="number" min="0" max="4"/><br/><br/>

            <label for="userRegistrationDate">Registration Date (currently ${user.getRegistrationDate().toString()}):</label>
            <input id="userRegistrationDate" value="${user.getRegistrationDate()}" name="userRegistrationDate" type="date"/><br/><br/>

            <label for="userName">Name (currently ${user.getName()}):</label>
            <input id="userName" value="${user.getName()}" name="userName" type="text"/><br/><br/>

            <label for="userSurname">Surname (currently ${user.getSurname()}):</label>
            <input id="userSurname" value="${user.getSurname()}" name="userSurname" type="text"/><br/><br/>

            <label for="userSex">Sex (currently ${user.getSex()}):</label>
            <select id="userSex" name="userSex">
                <option value="${user.getSex()}" selected disabled hidden>${user.getSex()}</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="others">Others</option>
            </select><br><br>

            <label for="userDateOfBirth">Date of Birth (currently ${user.getDateOfBirth().toString()}):</label>
            <input id="userDateOfBirth" value="${user.getDateOfBirth()}" name="userDateOfBirth" type="date"/><br/><br/>

            <label for="userNationality">Nationality (currently ${user.getNationality()}):</label>
            <input id="userNationality" value="${user.getNationality()}" name="userNationality" type="text"/><br/><br/>

            <label for="userHomeCountryAddress">Home Country Address (currently Street: ${user.getHomeCountryAddress().getString("street")} | Number: ${user.getHomeCountryAddress().getString("number")} | City: ${user.getHomeCountryAddress().getString("city")} | ZIP: ${user.getHomeCountryAddress().getString("zip")} | Country: ${user.getHomeCountryAddress().getString("country")}:</label>
            <div class="inputContainer" id="userHomeCountryAddress">
                <input type="text" name="userHomeCountryAddress-street" id="userHomeCountryAddress-street" placeholder="${user.getHomeCountryAddress().getString("street")}">
                <input type="text" name="userHomeCountryAddress-number" id="userHomeCountryAddress-number" placeholder="${user.getHomeCountryAddress().getString("number")}">
                <input type="text" name="userHomeCountryAddress-city" id="userHomeCountryAddress-city" placeholder="${user.getHomeCountryAddress().getString("city")}">
                <input type="text" name="userHomeCountryAddress-zip" id="userHomeCountryAddress-zip" placeholder="${user.getHomeCountryAddress().getString("zip")}">
                <input type="text" name="userHomeCountryAddress-country" id="userHomeCountryAddress-country" placeholder="${user.getHomeCountryAddress().getString("country")}">
            </div><br>

            <label for="userHomeCountryUniversity">Home Country University (currently ${user.getHomeCountryUniversity()}):</label>
            <input id="userHomeCountryUniversity" value="${user.getHomeCountryUniversity()}" name="userHomeCountryUniversity" type="text"/><br/><br/>

            <label for="userPeriodOfStay">Period of Stay (currently ${user.getPeriodOfStay()}):</label>
            <input id="userPeriodOfStay" value="${user.getPeriodOfStay()}" name="userPeriodOfStay" type="number" min="0" max="2"/><br/><br/>
            <p>1=first semester; 2=both semesters</p>

            <label for="userPhoneNumber">Phone Number (currently ${user.getPhoneNumber()}):</label>
            <input id="userPhoneNumber" value="${user.getPhoneNumber()}" name="userPhoneNumber" type="text"/><br/><br/>

            <label for="userPaduaAddress">Padua Address (currently Street: ${user.getPaduaAddress().getString("street")} | Number: ${user.getPaduaAddress().getString("number")} | City: ${user.getPaduaAddress().getString("city")} | ZIP: ${user.getPaduaAddress().getString("zip")} | Country: ${user.getPaduaAddress().getString("country")}:</label>
            <div class="inputContainer" id="userPaduaAddress">
                <input type="text" name="userPaduaAddress-street" id="userPaduaAddress-street" placeholder="${user.getPaduaAddress().getString("street")}">
                <input type="text" name="userPaduaAddress-number" id="userPaduaAddress-number" placeholder="${user.getPaduaAddress().getString("number")}">
                <input type="text" name="userPaduaAddress-city" id="userPaduaAddress-city" placeholder="${user.getPaduaAddress().getString("city")}">
                <input type="text" name="userPaduaAddress-zip" id="userPaduaAddress-zip" placeholder="${user.getPaduaAddress().getString("zip")}">
                <input type="text" name="userPaduaAddress-country" id="userPaduaAddress-country" placeholder="${user.getPaduaAddress().getString("country")}">
            </div><br>

            <label for="userDocumentType">Document Type (currently ${user.getDocumentType()}):</label>
            <select id="userDocumentType" name="userDocumentType">
                <option value="${user.getDocumentType()}" selected disabled hidden>${user.getDocumentType()}</option>
                <option value="ID">ID</option>
                <option value="Passport">Passport</option>
                <option value="Driver license">Driver license</option>
            </select><br><br>

            <label for="userDocumentNumber">Document Number (currently ${user.getDocumentNumber()}):</label>
            <input id="userDocumentNumber" value="${user.getDocumentNumber()}" name="userDocumentNumber" type="text"/><br/><br/>

            <label for="userDocumentFile">Document File (currently ${user.getDocumentFile()}):</label>
            <input id="userDocumentFile" value="${user.getDocumentFile()}" name="userDocumentFile" type="text" style="width:600px;"/><br/><br/>

            <label for="userDietType">Diet (currently ${user.getDietType()}):</label>
            <select id="userDietType" name="userDietType">
                <option value="${user.getDietType()}" selected disabled hidden>${user.getDietType()}</option>
                <option value="No specific">No specific</option>
                <option value="Vegetarian">Vegetarian</option>
                <option value="Vegan">Vegan</option>
                <option value="Halal">Halal</option>
                <option value="Kosher">Kosher</option>
                <option value="Pescatarian">Pescatarian</option>
            </select><br><br>

            <label for="userAllergies">Allergies (currently ${Arrays.toString(user.getAllergies())}. Separate by comma):</label>
            <input id="userAllergies" value="${Arrays.toString(user.getAllergies()).replaceAll("[^a-zA-Z\\p{Zs},]", "")}" name="userAllergies" type="text" style="width:1000px;"/><br/><br/>

            <input type="hidden" name="userEmailHash" value="${user.getEmailHash()}"/>

            <label for="userEmailConfirmed">Email Confirmed (currently ${user.getEmailConfirmed()}):</label>
            <select id="userEmailConfirmed" name="userEmailConfirmed">
                <option value="${user.getEmailConfirmed()}" selected disabled hidden>${user.getEmailConfirmed()}</option>
                <option value="true">true</option>
                <option value="false">false</option>
            </select><br><br>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
