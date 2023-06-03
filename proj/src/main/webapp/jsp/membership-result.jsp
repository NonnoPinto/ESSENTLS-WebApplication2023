<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="description" content="ESN Padova application">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="ISO-8859-1">
	<title>Membership Result</title>
	<%@ include file="../html/favicon.html" %>
	<%@ include file="../html/cdn.html" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
	<div class="row justify-content-center my-4">
		<div class="col-md-10">
			<div class="card text-center border-orange">
				<h1 class="card-title bg-orange color-white p-4">Membership Submission</h1>
				<div class="card-body">
					<c:choose>
						<c:when test="${user==null}">
							<div class="my-2 mx-4">
								<div class="pb-2">
									<h3>Something went wrong.</h3>
									<h5>${message}</h5>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="my-2 mx-4">
								<div class="pb-2">
									<h3>Membership submitted successfully.</h3>
								</div>
								<div class="my-4">
										<%--                                    <p class="pb-2 border-bottom border-gray">Registration--%>
										<%--                                        Date: ${user.registrationDate}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Name: ${user.name}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Surname: ${user.surname}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Sex: ${user.sex}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Date of Birth: ${user.dateOfBirth}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Nationality: ${user.nationality}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Home Country Address--%>
										<%--                                        (Street: ${user.getHomeCountryAddress().getString("street")} |--%>
										<%--                                        Number: ${user.getHomeCountryAddress().getString("number")} |--%>
										<%--                                        City: ${user.getHomeCountryAddress().getString("city")} |--%>
										<%--                                        ZIP: ${user.getHomeCountryAddress().getString("zip")} |--%>
										<%--                                        Country: ${user.getHomeCountryAddress().getString("country")})</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Home Country--%>
										<%--                                        University: ${user.homeCountryUniversity}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Period of Stay: ${user.periodOfStay}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Phone Number: ${user.phoneNumber}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Padua Address--%>
										<%--                                        (Street: ${user.getPaduaAddress().getString("street")} |--%>
										<%--                                        Number: ${user.getPaduaAddress().getString("number")} |--%>
										<%--                                        City: ${user.getPaduaAddress().getString("city")} |--%>
										<%--                                        ZIP: ${user.getPaduaAddress().getString("zip")} |--%>
										<%--                                        Country: ${user.getPaduaAddress().getString("country")})</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Document Type: ${user.documentType}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Document--%>
										<%--                                        Number: ${user.documentNumber}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Document File: ${user.documentFile}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Diet Type: ${user.dietType}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">--%>
										<%--                                        Allergies: ${Arrays.toString(user.getAllergies())}</p>--%>
										<%--                                    <p class="pb-2 border-bottom border-gray">Email--%>
										<%--                                        Confirmed: ${user.emailConfirmed}</p>--%>

									<p>
										<b>NEXT STEP: </b> Go to the office in order to sign and complete the
										membership!<br/>
										<br/>
										Meanwhile you can speed up the procedure by paying online if you wish.<br/>
										By paying online you will get a temporary card number, and you will be able										to
										view and subscribe at the events!<br/>
										<b>IMPORTANT: </b>Be careful that you must sign the papers in the office before actually
										partecipate at one event.
									</p>
									How do you want to pay for the membership?<br/>
									<a href="payment?action=sub">
										<button class="button bg-orange text-white border-orange px-4 py-2">ONLINE
										</button>
									</a>
									<a href="home">
										<button class="button bg-orange text-white border-orange px-4 py-2">CASH
										</button>
									</a>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="/html/footer.html" %>
</body>
</html>