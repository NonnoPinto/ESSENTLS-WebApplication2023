<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <title>Membership</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../html/cdn.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <div class="row justify-content-center my-4">
        <div class="col-lg-10 col-md-12">
            <div class="card text-center border-orange">
                <h2 class="card-title bg-orange color-white p-4">Membership </h2>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${message.error}">
                            <p><c:out value="${message.message}"/></p>
                        </c:when>
                        <c:otherwise>

                            <form method="POST" action="<c:url value="/membership"/>" enctype="multipart/form-data">

                                    <%--SEX--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="sex" class="mb-2 text-left">Please select your gender:</label>
                                    </div>
                                    <div>
                                        <select name="sex" id="sex" class="form-select">
                                            <option value="male">Male</option>
                                            <option value="female">Female</option>
                                            <option value="others">Others</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="first_name" class="mb-2 text-left">Name:</label>
                                    </div>
                                    <div class="input-container">
                                        <input name="first_name" maxlength="50" id="first_name" type="text" class="form-control"
                                               required>
                                    </div>
                                </div>

                                    <%--Surname--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="last_name" class="mb-2 text-left">Surname:</label>
                                    </div>
                                    <div class="input-container">
                                        <input name="last_name" maxlength="50" id="last_name" type="text" class="form-control"
                                               required>
                                    </div>
                                </div>

                                    <%--Date of Birth--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="birth-date" class="mb-2 text-left">Date of Birth:</label>
                                    </div>
                                    <div>
                                        <input name="birth-date" id="birth-date" type="date" class="form-control"
                                               required>
                                    </div>
                                </div>


                                    <%--Nationality--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="nationality" class="mb-2 text-left">Nationality:</label>
                                    </div>
                                    <div>
                                        <input name="nationality" maxlength="100" id="nationality" type="text" class="form-control"
                                               required>
                                    </div>
                                </div>

                                    <%--Home Country University--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="home-country-university" class="mb-2 text-left">From which
                                            university are you from?</label>
                                    </div>
                                    <div>
                                        <input name="home-country-university" maxlength="150" id="home-country-university" type="text"
                                               class="form-control" required>
                                    </div>
                                </div>


                                    <%--Home Country Address--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="home-country-address" class="mb-2 text-left">Please insert here your
                                            home-country
                                            address:</label>
                                    </div>
                                    <div class="container">

                                        <div class="row input-container" id="home-country-address">
                                            <div class="col-md-6 col-sm-12">
                                                <label for="home-country-address-street" class="form-label">Street</label>
                                                <input type="text" maxlength="100" name="home-country-address-street"
                                                id="home-country-address-street" class="form-control"
                                                placeholder="Enter Street...">
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <label for="home-country-address-number" class="form-label">Number</label>
                                                <input type="text" maxlength="5" name="home-country-address-number"
                                                id="home-country-address-number" class="form-control"
                                                placeholder="Enter number...">
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <label for="home-country-address-city" class="form-label">City</label>
                                                <input type="text" maxlength="60" name="home-country-address-city"
                                                id="home-country-address-city" class="form-control"
                                                placeholder="Enter city...">
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <label for="home-country-address-zip" class="form-label">ZIP</label>
                                                <input type="text" maxlength="9" name="home-country-address-zip"
                                                id="home-country-address-zip" class="form-control"
                                                placeholder="Enter zip...">
                                            </div>
                                            <div class="col-md-6 col-sm-12">
                                                <label for="home-country-address-country" class="form-label">Country</label>
                                                <input type="text" maxlength="55" name="home-country-address-country"
                                                id="home-country-address-country" class="form-control"
                                                placeholder="Enter country...">
                                            </div>
                                        </div>
                                    </div>
                                        
                                </div>


                                    <%--Period of Stay--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="period-of-stay" class="mb-2 text-left">Period of stay (consider only
                                            this
                                            academic year):</label>
                                    </div>
                                    <div>
                                        <select name="period-of-stay" id="period-of-stay" class="form-select">
                                            <option value="1">(one semester)</option>
                                            <option value="2">(both semesters)</option>
                                        </select>
                                    </div>
                                </div>

                                    <%--Phone Number--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="phone-number" class="mb-2 text-left">Phone number:</label>
                                    </div>
                                    <div>
                                        <input id="phone-number" maxlength="50" name="phone-number" type="text" class="form-control"
                                               required>
                                    </div>
                                </div>

                                    <%--Padua Address--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="padua-address" class="mb-2 text-left">Padua address:</label>
                                    </div>
                                    <div class="row justify-content-start input-container" id="padua-address">
                                        <div class="col-md-6 col-sm-12">
                                            <label for="padua-address-street">Street</label>
                                            <input type="text" maxlength="35" name="padua-address-street" id="padua-address-street"
                                                   class="form-control" placeholder="Enter Street...">
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <label for="padua-address-number">Number</label>
                                            <input type="text" maxlength="5" name="padua-address-number" id="padua-address-number"
                                                   class="form-control" placeholder="Enter number...">
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <label for="padua-address-city">City</label>
                                            <input type="text" maxlength="25" name="padua-address-city" id="padua-address-city"
                                                   class="form-control" placeholder="Enter city...">
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <label for="padua-address-zip">ZIP</label>
                                            <input type="text" maxlength="9" name="padua-address-zip" id="padua-address-zip"
                                                   class="form-control" placeholder="Enter zip...">
                                        </div>
                                        <div class="col-md-6 col-sm-12">
                                            <label for="padua-address-country">Country</label>
                                            <input type="text" maxlength="55" name="padua-address-country" id="padua-address-country"
                                                   class="form-control" placeholder="Enter country...">
                                        </div>
                                    </div>
                                </div>

                                    <%--DocumentType--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="document-type" class="mb-2 text-left">Document type:</label>
                                    </div>
                                    <div>
                                        <select name="document-type" id="document-type" class="form-select">
                                            <option value="ID">ID</option>
                                            <option value="Passport">Passport</option>
                                            <option value="Driver license">Driver license</option>
                                        </select>
                                    </div>
                                </div>

                                    <%--DocumentNumber--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="document-number" class="mb-2 text-left">Document number:</label>
                                    </div>
                                    <div>
                                        <input name="document-number" maxlength="50" id="document-number" type="text"
                                               class="form-control"
                                               required>
                                    </div>
                                </div>

                                    <%--Diet Type--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="diet-type" class="mb-2 text-left">Diet type:</label>
                                    </div>
                                    <div>
                                        <select id="diet-type" name="diet-type" class="form-select">
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
                                        <label for="allergies" class="mb-2 text-left">Allergies: (please use commas to
                                            separate
                                            them):</label>
                                    </div>
                                    <div>
                                        <input id="allergies" maxlength="255" name="allergies" type="text" class="form-control">
                                    </div>
                                </div>

                                    <%--Upload New Document--%>
                                <div class="mx-2 my-2 d-flex justify-content-start">
                                    <label for="document-bytes">Upload Document:</label>
                                    <input id="document-bytes" accept="application/pdf" name="document-bytes" type="file" class="mx-2" required/>
                                </div>

                                    <%--Button--%>
                                <div class="row d-flex my-4">
                                    <div class="mb-3">
                                        <button type="submit"
                                                class="button bg-orange text-white border-orange px-4 py-2">
                                            Submit
                                        </button>
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