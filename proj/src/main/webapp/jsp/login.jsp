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

<div class="container">
	<h1 class="page-title text-center p-2">Welcome to ESN!</h1>
	<div class="row justify-content-center my-4">
		<div class="col-md-6">
			<div class="card text-center border-orange">
				<h2 class="card-title bg-orange color-white p-4"> Login to cotinue</h2>
				<div class="card-body">

					<!--<form action="<c:url value="/login"/>" id="loginForm" method="POST" enctype="multipart/form-data">-->
					<!-- ---------------- EMAIL -------------------- -->
					<div class="form-group mb-4">
						<div class="d-flex justify-content-start">
							<label for="email" class="mb-2 text-left">Insert your Email</label>
						</div>
						<div class="input-container">
							<input class="form-control" type="text" name="email" id="email" placeholder="Email.." required>
							<span id="infoEmail"></span>
						</div>
					</div>
					<!-- ---------------- PASSWORD -------------------- -->
					<div class="form-group my-4">
						<div class="d-flex justify-content-start">
							<label for="password" class="mb-2 text-left">Insert your Password</label>
						</div>
						<div class="input-container">
							<input class="form-control" type="password" name="password" id="password" placeholder="Password.." required>
							<i class="togglePassword far fa-eye" id="togglePassword"></i>
							<span id="infoPassword"></span>
						</div>
					</div>
					<!-- ---------------- MESSAGE --------------------
						<c:choose>
							<c:when test="${message.isError()}">
								<div>
									<div class="alert alert-danger" role="alert">
										<c:out value="${message.message}"/>
									</div>
								</div>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						-->
					<div id="errorMessageDiv" style="visibility: hidden;">

					</div>
					<!-- ---------------- SUBMIT BUTTON -------------------- -->
					<div>
						<button type="submit" class="button bg-orange text-white border-orange px-4 py-2" id="loginButton">Log in</button>
					</div>
					<p class="card-text p-2">
						<small class="form-text text-muted">
							Don't have an account? <a href="<c:url value="/signup"/>" >Sign up</a>
						</small>
					</p>

				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/js/toggle_password.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/ajax_login.js"/>"></script>
<%@include file="/html/footer.html"%>
</body>
</html>