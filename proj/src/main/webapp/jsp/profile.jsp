<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17/4/2023
  Time: 12:30 am
  To change this template use File | Settings | File Templates.
--%>
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
    <title>User details</title>
    <%@ include file="../html/favicon.html"%>
  </head>
  <body>
  <%@include file="navbar.jsp"%>
    <div class="container">
      <div class="row justify-content-center my-4">
        <div class="col-md-10">
          <div class="card text-center border-magenta">
            <h1 class="card-title bg-magenta color-white p-4">Profile Page</h1>
            <div class="card-body">
              <div class="my-2 mx-4">
                <div class="pb-2">
                  <h3>${Users.getName()} ${Users.getSurname()}</h3>
                  <h5>Sex: ${Users.getSex()}</h5>
                  <h5>Nationality: ${Users.getNationality()}</h5>
                  <hr>
                </div>
                <div class="my-4">
                  <p class="pb-2 border-bottom border-gray">Email: ${Users.getEmail()}</p>
                  <p class="pb-2 border-bottom border-gray">Card ID: ${Users.getCardId()}</p>
                  <p class="pb-2 border-bottom border-gray">Date of Birth: ${Users.getDateOfBirth().toString()}</p>
                  <p class="pb-2 border-bottom border-gray">Registration Date: ${Users.getRegistrationDate().toString()}</p>
                  <p class="pb-2 border-bottom border-gray">Home Country Address (Street: ${Users.getHomeCountryAddress().getString("street")} | Number: ${Users.getHomeCountryAddress().getString("number")} | City: ${Users.getHomeCountryAddress().getString("city")} | ZIP: ${Users.getHomeCountryAddress().getString("zip")} | Country: ${Users.getHomeCountryAddress().getString("country")})</p>
                  <p class="pb-2 border-bottom border-gray">Home Country University: ${Users.getHomeCountryUniversity()}</p>
                  <p class="pb-2 border-bottom border-gray">Period of Stay: ${Users.getPeriodOfStay()}</p>
                  <p class="pb-2 border-bottom border-gray">Phone Number: ${Users.getPhoneNumber()}</p>
                  <p class="pb-2 border-bottom border-gray">Padua Address (Street: ${Users.getPaduaAddress().getString("street")} | Number: ${Users.getPaduaAddress().getString("number")} | City: ${Users.getPaduaAddress().getString("city")} | ZIP: ${Users.getPaduaAddress().getString("zip")} | Country: ${Users.getPaduaAddress().getString("country")})</p>
                  <p class="pb-2 border-bottom border-gray">Document Number: ${Users.getDocumentNumber()}</p>
                  <p class="pb-2 border-bottom border-gray">Diet: ${Users.getDietType()}</p>
                  <p class="pb-2 border-bottom border-gray">Allergies: ${Arrays.toString(Users.getAllergies())}</p>
                  <p class="pb-2 border-bottom border-gray">Email Confirmed: ${Users.getEmailConfirmed()}</p>
                </div>
              </div>
              <%-------------Edit Profile and Show Payment List Button------------%>
              <div class="row d-flex justify-content-center my-2">
                <form action="<c:url value="/edit-profile"/>" method="GET" class="col-md-6">
                  <div>
                    <button type="submit" class="button bg-magenta text-white border-magenta px-4 py-2" id="editProfileButton">Edit Profile</button>
                  </div>
                </form>
                <form action="<c:url value="/paymentslist"/>" method="GET" class="col-md-6">
                  <div>
                    <button type="submit" class="button bg-magenta text-white border-magenta px-4 py-2" id="paymentListButton">Payment List</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  <%@include file="/html/footer.html"%>
  </body>
</html>
