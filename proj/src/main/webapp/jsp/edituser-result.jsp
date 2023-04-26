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
    <meta name="description" content="ESN Padova application">
    <meta name="viewport">
    <title>User Edit</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>
<div class="title">
    <h1>User Edit</h1>
    <hr>
</div>

<c:choose>
    <c:when test="${user==null}">
        <h2>Something went wrong.</h2>
        <c:out value="${message}"/>
    </c:when>
    <c:otherwise>
        <c:out value="${user.getId()} successfully edited."/>
        <br/>
        <c:out value="Email: ${user.getEmail()}"/>
        <br/>
        <c:out value="CardId: ${user.getCardId()}"/>
        <br/>
        <c:out value="Tier: ${user.getTier()}"/>
        <br/>
        <c:out value="Registration Date: ${user.getRegistrationDate()}"/>
        <br/>
        <c:out value="Name: ${user.getName()}"/>
        <br/>
        <c:out value="Surname: ${user.getSurname()}"/>
        <br/>
        <c:out value="Sex: ${user.getSex()}"/>
        <br/>
        <c:out value="Date of Birth: ${user.getDateOfBirth()}"/>
        <br/>
        <c:out value="Nationality: ${user.getNationality()}"/>
        <br/>
        <c:out value="Home Country Address: ${user.getHomeCountryAddress()}"/>
        <br/>
        <c:out value="Home Counrty University: ${user.getHomeCountryUniversity()}"/>
        <br/>
        <c:out value="Period of Stay: ${user.getPeriodOfStay()}"/>
        <br/>
        <c:out value="Phone Number: ${user.getPhoneNumber()}"/>
        <br/>
        <c:out value="Padua Address: ${user.getPaduaAddress()}"/>
        <br/>
        <c:out value="Document Type: ${user.getDocumentType()}"/>
        <br/>
        <c:out value="Document Number: ${user.getDocumentNumber()}"/>
        <br/>
        <c:out value="Document File: ${user.getDocumentFile()}"/>
        <br/>
        <c:out value="Diet Type: ${user.getDietType()}"/>
        <br/>
        <c:out value="Allergies: ${Arrays.toString(user.getAllergies())}"/>
        <br/>
        <c:out value="Email Confirmed: ${user.getEmailConfirmed()}"/>
        <br/>
    </c:otherwise>
</c:choose>



</body>
</html>