<%--
  Created by IntelliJ IDEA.
  User: Vaidas
  Date: 17/04/2023
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        <c:out value="ID: ${user.getId().toString()}"/>
        <form method="POST" action="<c:url value="/edit-user"/>">
            <input type="hidden" name="userId" value="${user.getId()}"/>
            <label for="userEmail">Email (currently ${user.getEmail()}):</label>
            <input id="userEmail" value="${user.getEmail()}" name="userEmail" type="text"/><br/><br/>
            <input type="hidden" name="userPassword" value="${user.getPassword()}"/>
            <label for="userCardId">CardId (currently ${user.getCardId()}):</label>
            <input id="userCardId" value="${user.getCardId()}" name="userCardId" type="text"/><br/><br/>
            <label for="userTier">Tier (currently ${user.getTier()}):</label>
            <input id="userTier" value="${user.getTier()}" name="userTier" type="text"/><br/><br/>
            <label for="userRegistrationDate">Registration Date (currently ${user.getRegistrationDate().toString()}):</label>
            <input id="userRegistrationDate" value="${user.getRegistrationDate()}" name="userRegistrationDate" type="text"/><br/><br/>
            <label for="userName">Name (currently ${user.getName()}):</label>
            <input id="userName" value="${user.getName()}" name="userName" type="text"/><br/><br/>
            <label for="userSurname">Surname (currently ${user.getSurname()}):</label>
            <input id="userSurname" value="${user.getSurname()}" name="userSurname" type="text"/><br/><br/>
            <label for="userSex">Sex (currently ${user.getSex()})</label>
            <input id="userSex" value="${user.getSex()}" name="userSex" type="text"/><br/><br/>
            <label for="userDateOfBirth">Date of Birth (currently ${user.getDateOfBirth().toString()}):</label>
            <input id="userDateOfBirth" value="${user.getDateOfBirth()}" name="userDateOfBirth" type="text"/><br/><br/>
            <label for="userNationality">Nationality (currently ${user.getNationality()}):</label>
            <input id="userNationality" value="${user.getNationality()}" name="userNationality" type="text"/><br/><br/>
            <label for="userHomeCountryAddress">Home Country Address (currently ${user.getHomeCountryAddress()}):</label>
            <input id="userHomeCountryAddress" value="${user.getHomeCountryAddress()}" name="userHomeCountryAddress" type="text"/><br/><br/>
            <label for="userHomeCountryUniversity">Home Country University (currently ${user.getHomeCountryUniversity()}):</label>
            <input id="userHomeCountryUniversity" value="${user.getHomeCountryUniversity()}" name="userHomeCountryUniversity" type="text"/><br/><br/>
            <label for="userPeriodOfStay">Period of Stay (currently ${user.getPeriodOfStay()}):</label>
            <input id="userPeriodOfStay" value="${user.getPeriodOfStay()}" name="userPeriodOfStay" type="text"/><br/><br/>
            <label for="userPhoneNumber">Phone Number (currently ${user.getPhoneNumber()}):</label>
            <input id="userPhoneNumber" value="${user.getPhoneNumber()}" name="userPhoneNumber" type="text"/><br/><br/>
            <label for="userPaduaAddress">Padua Address (currently ${user.getPaduaAddress()}):</label>
            <input id="userPaduaAddress" value="${user.getPaduaAddress()}" name="userPaduaAddress" type="text"/><br/><br/>
            <label for="userDocumentType">Document Type (currently ${user.getDocumentType()}):</label>
            <input id="userDocumentType" value="${user.getDocumentType()}" name="userDocumentType" type="text"/><br/><br/>
            <label for="userDocumentNumber">Document Number (currently ${user.getDocumentNumber()}):</label>
            <input id="userDocumentNumber" value="${user.getDocumentNumber()}" name="userDocumentNumber" type="text"/><br/><br/>
            <label for="userDocumentFile">Document File (currently ${user.getDocumentFile()}):</label>
            <input id="userDocumentFile" value="${user.getDocumentFile()}" name="userDocumentFile" type="text"/><br/><br/>
            <label for="userDietType">Diet Type (currently ${user.getDietType()}):</label>
            <input id="userDietType" value="${user.getDietType()}" name="userDietType" type="text"/><br/><br/>
            <label for="userAllergies">Allergies (currently ${user.getAllergies()}):</label>
            <input id="userAllergies" value="${user.getAllergies()}" name="userAllergies" type="text"/><br/><br/>
            <input type="hidden" name="userEmailHash" value="${user.getEmailHash()}"/>
            <label for="userEmailConfirmed">Email Confirmed (currently ${user.getEmailConfirmed()}):</label>
            <input id="userEmailConfirmed" value="${user.getEmailConfirmed()}" name="userEmailConfirmed" type="text"/><br/><br/>


            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
