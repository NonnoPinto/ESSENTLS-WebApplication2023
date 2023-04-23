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
    

    <form method="POST" action="<c:url value="/register"/>">
    <!-- TODO: check all types and names (19 params) -->
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
            <label for="home-country-address">Home country address:</label>
            <input name="home-country-address" type="text" required><br><br>
            <label for="home-country-university">Home country university:</label>
            <input name="home-country-university" type="text" required><br><br>
            <!-- TODO: decide if we want to use a double field or a single input,let's keep it -->
            <!-- <p>Period of stay:</p>
            <label for="period-of-stay-from">Period of stay from:</label>
            <input name="period-of-stay-from" type="date" type="text" required><br><br>
            <label for="period-of-stay-to">Period of stay to:</label>
            <input name="period-of-stay-to" type="date" type="text" required><br><br> -->
            <label for="period-of-stay">Period of stay:</label>
            <input name="period-of-stay" type="text" required><br><br>
            <label for="phone-number">Phone number:</label>
            <input name="phone-number" type="text" required><br><br>
            <label for="padua-address">Padua address:</label>
            <input name="padua-address" type="text" required><br><br>
            <label for="card-id">Card ID:</label>
            <input name="card-id" type="text" required><br><br>
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

    <c:choose>
        <c:when test="${message.error}">
            <p><c:out value="${message.message}"/></p>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
</body>
</html>