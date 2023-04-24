<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Register</title>
</head>
<body>
    <div class="navbar"><%@include file="navbar.jsp"%></div>

     <c:choose>
        <c:when test="${message.error}">
            <p><c:out value="${message.message}"/></p>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>

    <form method="POST" action="<c:url value="/register"/>">
        <div>
            <label for="email">Enter your email:</label>
            <input name="email" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="text"/><br/><br/>
            <!-- if we want to block mails with + in the first argument, this can be a trick to register multiple with gmail,.. -->
            <!-- <input name="email" pattern="^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="text"/><br/><br/> -->
            <label for="password">password:</label>
            <input name="password" type="password"/><br/><br/>
            <label for="rpassword">repeat password:</label>
            <input name="rpassword" type="password"/><br/><br/>
        </div>

        <div>
            <label for="sex">Please select your gender:</label>
            <select name="sex"> 
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="others">Others</option>
            </select><br><br>
            <label for="card-id">Card ID:</label>
            <input name="card-id" type="text" required><br><br>
            <label for="first_name">First Name:</label>
            <input name="first_name" type="text" required><br><br>
            <label for="last_name">Last Name:</label>
            <input name="last_name" type="text" required><br><br>
            <label for="birth-date">Date of birth:</label>
            <input name="birth-date" type="date" required><br><br>
            <label for="nationality">Nationality:</label>
            <input name="nationality" type="text" required><br><br>
            <label for="home-country-university">From which university are you from?</label>
            <input name="home-country-university" type="text" required><br><br>
            <label for="home-country-address">Please insert here your home-country address:</label>
            <div class="inputContainer">
                <input type="text" name="home-country-address-province" id="home-country-address-province" placeholder="Enter province...">
                <input type="text" name="home-country-address-city" id="home-country-address-city" placeholder="Enter city...">
                <input type="text" name="home-country-address-street" id="home-country-address-street" placeholder="Enter Street...">
            </div>
            <label for="period-of-stay">Period of stay (consider only this academic year):</label>
            <select name="period-of-stay">
                <option value="1">One Semester</option>
                <option value="2">Both semesters</option>
            </select><br><br>
            <label for="phone-number">Phone number:</label>
            <input name="phone-number" type="text" required><br><br>
            <label for="padua-address">Padua address:</label>
            <div class="inputContainer">
                <input type="text" name="padua-address-province" id="padua-address-province" placeholder="Enter province...">
                <input type="text" name="padua-address-city" id="padua-address-city" placeholder="Enter city...">
                <input type="text" name="padua-address-street" id="padua-address-street" placeholder="Enter Street...">
            </div>
            <!-- <input name="padua-address" type="text" required><br><br> -->
            <label for="document-type">Document type:</label>
            <select name="document-type">
                <option value="ID">ID</option>
                <option value="Passport">Passport</option>
                <option value="Driver license">Driver license</option>
            </select><br><br>
            <label for="document-number">Document number:</label>
            <input name="document-number" type="text" required><br><br>
            <label for="document-file">Document file:</label>
            <input name="document-file" type="text" required/><br><br>
            <label for="diet-type">Diet type:</label>
            <select name="diet-type">
                <option value="No specific">No specific</option>
                <option value="Vegetarian">Vegetarian</option>
                <option value="Vegan">Vegan</option>
                <option value="Halal">Halal</option>
                <option value="Kosher">Kosher</option>
                <option value="Pescatarian">Pescatarian</option>
            </select><br><br>
            <label for="allergies">Allergies: (please use commas to separate them)</label>
            <input name="allergies" type="text"><br><br>
        </div>



        <button type="submit">Submit</button><br/>

    </form>


</body>
</html>