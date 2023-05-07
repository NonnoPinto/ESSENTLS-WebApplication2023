<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="description" content="ESN Padova application">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- @ include file="/html/cdn.html"--%>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
    <%@include file="navbar.jsp"%>

	<div class="loginContainer">
		<div class="loginTitle">
			<h1>LOG IN</h1>
			<hr>
		</div>
		<form action="<c:url value="/login"/>" id="loginForm" method="POST" enctype="multipart/form-data">

			<!-- ---------------- EMAIL -------------------- -->
			<div class="formDiv" style="padding-top: 10px;">
				<label>Insert your Email</label>
				<div class="input-container">
					<input type="text" name="email" id="email" placeholder="Email.." required>
					<span id="infoEmail"></span>
				</div>
			</div>
			<!-- ---------------- PASSWORD -------------------- -->
			<div class="formDiv" style="padding-top: 10px;">
				<label>Insert your Password</label>
				<div class="input-container">
					<input type="password" name="password" id="password" placeholder="Password.." required>
					<span id="infoPassword"></span>
				</div>
			</div>
            <!-- ---------------- SUBMIT BUTTON -------------------- -->
			<div>
				<button type="submit">Login</button>
			</div>
			<c:choose>
				<c:when test="${message.isError()}">
					<div>
						<div role="alert">
                                <span>
                                    <c:out value="${message.message}"/>
                                </span>
						</div>
					</div>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>

		</form>


		<p>
			Don't have an account? <a href="<c:url value="/signup"/>" >Sign up</a>
		</p>
	</div>
	<%@include file="/html/footer.html"%>
</body>
</html>