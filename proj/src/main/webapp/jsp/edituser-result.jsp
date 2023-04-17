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
    <title>User Edit</title>
</head>

<body>
<h1>User Edit</h1>

<c:choose>
    <c:when test="${user==null}">
        <h2>Fail</h2>
        <c:out value="${message}"/>
    </c:when>
    <c:otherwise>
        <c:out value="${user.getId()} successfully edited."/>
        <br/>
        <c:out value="Email: ${user.getEmail()}"/>
        <c:out value="CardId: ${user.getCardId()}"/>
        <c:out value="Tier: ${user.getTier()}"/>
        <c:out value="Registration Date: ${user.getRegistrationDate()}"/>
        <c:out value="Name: ${user.getName()}"/>
        <c:out value="Surname: ${user.getSurname()}"/>
        <c:out value="Sex: ${user.getSex()}"/>
        <c:out value="Date of Birth: ${user.getDateOfBirth()}"/>
        <c:out value="Nationality: ${user.getNationality()}"/>
        <c:out value="Home Country Address: ${user.getHomeCountryAddress()}"/>
        <c:out value="Home Counrty University: ${user.getHomeCountryUniversity()}"/>
        <c:out value="Period of Stay: ${user.getPeriodOfStay()}"/>
        <c:out value="Phone Nuumber: ${user.getPhoneNumber()}"/>
        <c:out value="Padua Address: ${user.getPaduaAddress()}"/>
        <c:out value="Document Type: ${user.getDocumentType()}"/>
        <c:out value="Document Number: ${user.getDocumentNumber()}"/>
        <c:out value="Document File: ${user.getDocumentFile()}"/>
        <c:out value="Diet Type: ${user.getDietType()}"/>
        <c:out value="Allergies: ${user.getAllergies()}"/>
        <c:out value="Email Confirmed: ${user.getEmailConfirmed()}"/>
    </c:otherwise>
</c:choose>



</body>
</html>