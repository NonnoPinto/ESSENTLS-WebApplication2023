<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <title>Edit User</title>
        <%@ include file="../html/favicon.html"%>
        <%@ include file="../html/cdn.html"%>
    </head>

    <body>
    <%@include file="navbar.jsp"%>

        <div class="container">
            <div class="row justify-content-center my-4">
                <div class="col-md-10">
                    <div class="card text-center border-orange">
                        <h2 class="card-title bg-orange color-white p-4">Edit User</h2>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${user == null}">
                                    <p>User not found</p>
                                </c:when>
                                <c:otherwise>
                                    <div class=" mx-2 my-2 d-flex justify-content-start">
                                        <c:out value="ID: ${user.getId().toString()}"/>
                                    </div>
                                    <form method="POST" action="<c:url value="/edit-user"/>" enctype="multipart/form-data">
                                            <%--ID--%>
                                        <input type="hidden" name="userId" value="${user.getId()}"/>

                                            <%--Email--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userEmail" class="mb-2 text-left">Email (currently ${user.getEmail()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userEmail" value="${user.getEmail()}" maxlength="254" name="userEmail" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Password--%>
                                        <input type="hidden" name="userPassword" maxlength="255" value="${user.getPassword()}"/>

                                            <%--CardId--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userCardId" class="mb-2 text-left">CardId (currently ${user.getCardId()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userCardId" value="${user.getCardId()}" maxlength="50" name="userCardId" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Tier--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userTier" class="mb-2 text-left">Tier (currently ${user.getTier()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userTier" value="${user.getTier()}" name="userTier" type="number" min="0" max="4" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Registration Date--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userRegistrationDate" class="mb-2 text-left">Registration Date (currently ${user.getRegistrationDate().toString()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userRegistrationDate" value="${user.getRegistrationDate()}" name="userRegistrationDate" type="date" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Name--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userName" class="mb-2 text-left">Name (currently ${user.getName()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userName" maxlength="50" value="${user.getName()}" name="userName" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Surname--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userSurname" class="mb-2 text-left">Surname (currently ${user.getSurname()}):</label>
                                            </div>
                                            <div class="input-container">
                                                <input id="userSurname" maxlength="50" value="${user.getSurname()}" name="userSurname" type="text" class="form-control"/>
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
                                                <input id="userNationality" maxlength="100" value="${user.getNationality()}" name="userNationality" type="text" class="form-control"/>
                                            </div>
                                        </div>


                                            <%--Home Country Address--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userHomeCountryAddress" class="mb-2 text-left">Home Country Address (currently Street: ${user.getHomeCountryAddress().getString("street")} | Number: ${user.getHomeCountryAddress().getString("number")} | City: ${user.getHomeCountryAddress().getString("city")} | ZIP: ${user.getHomeCountryAddress().getString("zip")} | Country: ${user.getHomeCountryAddress().getString("country")}):</label>
                                            </div>
                                            <div class="row input-container" id="userHomeCountryAddress">
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userHomeCountryAddress-street" class="form-label">Street</label>
                                                    <input type="text" name="userHomeCountryAddress-street" maxlength="100" id="userHomeCountryAddress-street" class="form-control" value="${user.getHomeCountryAddress().getString("street")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userHomeCountryAddress-number" class="form-label">Number</label>
                                                    <input type="text" name="userHomeCountryAddress-number" maxlength="5" id="userHomeCountryAddress-number" class="form-control" value="${user.getHomeCountryAddress().getString("number")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userHomeCountryAddress-city" class="form-label">City</label>
                                                    <input type="text" name="userHomeCountryAddress-city" maxlength="60" id="userHomeCountryAddress-city" class="form-control" value="${user.getHomeCountryAddress().getString("city")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userHomeCountryAddress-zip" class="form-label">ZIP</label>
                                                    <input type="text" name="userHomeCountryAddress-zip" maxlength="9" id="userHomeCountryAddress-zip" class="form-control" value="${user.getHomeCountryAddress().getString("zip")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userHomeCountryAddress-country" class="form-label">Country</label>
                                                    <input type="text" name="userHomeCountryAddress-country" maxlength="55" id="userHomeCountryAddress-country" class="form-control" value="${user.getHomeCountryAddress().getString("country")}">
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
                                                <input id="userPhoneNumber" maxlength="50" value="${user.getPhoneNumber()}" name="userPhoneNumber" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Padua Address--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userPaduaAddress" class="mb-2 text-left">Padua Address (currently Street: ${user.getPaduaAddress().getString("street")} | Number: ${user.getPaduaAddress().getString("number")} | City: ${user.getPaduaAddress().getString("city")} | ZIP: ${user.getPaduaAddress().getString("zip")} | Country: ${user.getPaduaAddress().getString("country")}):</label>
                                            </div>
                                            <div class="row input-container" id="userPaduaAddress">
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userPaduaAddress-street">Street</label>
                                                    <input type="text" maxlength="35" name="userPaduaAddress-street" id="userPaduaAddress-street" class="form-control" value="${user.getPaduaAddress().getString("street")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userPaduaAddress-number">Number</label>
                                                    <input type="text" maxlength="5" name="userPaduaAddress-number" id="userPaduaAddress-number" class="form-control" value="${user.getPaduaAddress().getString("number")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userPaduaAddress-city">City</label>
                                                    <input type="text" maxlength="25" name="userPaduaAddress-city" id="userPaduaAddress-city" class="form-control" value="${user.getPaduaAddress().getString("city")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userPaduaAddress-zip">ZIP</label>
                                                    <input type="text" maxlength="9" name="userPaduaAddress-zip" id="userPaduaAddress-zip" class="form-control" value="${user.getPaduaAddress().getString("zip")}">
                                                </div>
                                                <div class="col-lg-4 col-md-6 col-sm-12">
                                                    <label for="userPaduaAddress-country">Country</label>
                                                    <input type="text" maxlength="55" name="userPaduaAddress-country" id="userPaduaAddress-country" class="form-control" value="${user.getPaduaAddress().getString("country")}">
                                                </div>
                                            </div>
                                        </div>

                                            <%--DocumentType--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userDocumentType" class="mb-2 text-left">Document Type (currently ${user.getDocumentType()}):</label>
                                            </div>
                                            <div>
                                                <select id="userDocumentType" name="userDocumentType" class="form-select">
                                                    <option value="${user.getDocumentType()}" selected hidden>${user.getDocumentType()}</option>
                                                    <option value="ID">ID</option>
                                                    <option value="Passport">Passport</option>
                                                    <option value="Driver license">Driver license</option>
                                                </select>
                                            </div>
                                        </div>


                                            <%--DocumentNumber--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userDocumentNumber" class="mb-2 text-left">Document Number (currently ${user.getDocumentNumber()}):</label>
                                            </div>
                                            <div>
                                                <input id="userDocumentNumber" maxlength="50" value="${user.getDocumentNumber()}" name="userDocumentNumber" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--Document File--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userDocumentFile" class="mb-2 text-left">Document File (currently ${user.getDocumentFile()}):</label>
                                            </div>
                                            <div>
                                                <input id="userDocumentFile" value="${user.getDocumentFile()}" name="userDocumentFile" type="text" class="form-control"/>
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
                                                <input id="userAllergies" maxlength="255" value="${Arrays.toString(user.getAllergies()).replaceAll("[^a-zA-Z\\p{Zs},]", "")}" name="userAllergies" type="text" class="form-control"/>
                                            </div>
                                        </div>

                                            <%--EmailHash--%>
                                        <input type="hidden" maxlength="255" name="userEmailHash" value="${user.getEmailHash()}"/>

                                            <%--Email Confirmed--%>
                                        <div class="mx-2 my-2">
                                            <div class="d-flex justify-content-start">
                                                <label for="userEmailConfirmed" class="mb-2 text-left">Email Confirmed (currently ${user.getEmailConfirmed()}):</label>
                                            </div>
                                            <div>
                                                <select id="userEmailConfirmed" name="userEmailConfirmed" class="form-select">
                                                    <option value="${user.getEmailConfirmed()}" selected hidden>${user.getEmailConfirmed()}</option>
                                                    <option value="true">true</option>
                                                    <option value="false">false</option>
                                                </select>
                                            </div>
                                        </div>

                                            <%--Upload New Document--%>
                                        <div class="mx-2 my-2 d-flex justify-content-start">
                                            <label for="userDocumentBytes">Upload New Document:</label>
                                            <input id="userDocumentBytes" accept="application/pdf" name="userDocumentBytes" type="file" class="mx-2"/>
                                        </div>

                                            <%--Button--%>
                                        <div class="row d-flex my-4">
                                            <div class="mb-3">
                                                <button type="submit" class="button bg-orange text-white border-orange px-4 py-2">Submit</button>
                                            </div>
                                            <div>
                                                <button type="reset" class="button bg-white color-orange border-orange px-4 py-2">Reset the form</button>
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
