<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>Register</title>
    </head>
    <body>

        <form method="POST" action="<c:url value="/user/register/"/>">
        <!-- TODO: check all types and names (19 params) -->
            <div>
                <label for="email">email:</label>
                <input name="email" type="text"/><br/><br/>

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
                <input name="document-type" type="text" required><br><br>
                <label for="document-number">Document number:</label>
                <input name="document-number" type="text" required><br><br>
                <label for="document-file">Document file:</label>
                <input name="document-file" type="file" type="text" required><br><br>
                <label for="diet-type">Diet type:</label>
                <input name="diet-type"><br><br>
                <label for="allergies">Allergies:</label>
                <input name="allergies"><br><br>
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