<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <title>Sign up</title>
    <%@ include file="../html/favicon.html"%>
    <%@ include file="../jsp/cdn.jsp"%>
</head>
<body>
    <%@include file="navbar.jsp"%>

    <div class="container">
    	<h1 class="page-title text-center p-2">Want to join? Fill the form!</h1>
        <div class="row justify-content-center my-4">
            <div class="col-md-6">
                <div class="card text-center border-cyan">
                    <h2 class="card-title bg-cyan color-white p-4">Sign up</h2>
                
                    <div class="card-body">
                    
                        <c:choose>
                            <c:when test="${message.isError()}">
                                <div>
                                    <div class="alert alert-danger" role="alert">
                                        <p><c:out value="${message.message}"/></p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>

                        <form method="POST" action="<c:url value="/register"/>">
                            <div>
                                <!--EMAIL-->
                                <div class="form-group my-4">
                                    <div class="d-flex justify-content-start">
                                        <label label for="email" class="mb-2 text-left">Insert your Email</label>
                                    </div>
                                    <div class="input-container">
                                        <input name="email" id="email" maxlength="254" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="email" class="form-control" placeholder="Email.." required/>
                                        <span id="infoEmail"></span>
                                    </div>
                                </div>

                                <!--PASSWORD-->
                                <div class="form-group my-4">
                                    <div class="d-flex justify-content-start">
                                        <label for="password" class="mb-2 text-left">Insert your Password</label>
                                    </div>
                                    <div class="input-container">
                                        <input class="form-control" maxlength="255" type="password" name="password" id="password" placeholder="Password.." required>
                                        <i class="toggle-password far fa-eye" id="togglePassword"></i>
                                        <span id="infoPassword"></span>
                                    </div>
                                </div>

                                <!--RE-PASSWORD-->
                                <div class="form-group my-4">
                                    <div class="d-flex justify-content-start">
                                        <label for="rpassword" class="mb-2 text-left">Repeat Password</label>
                                    </div>
                                    <div class="input-container">
                                        <input class="form-control" maxlength="255" type="password" name="rpassword" id="rpassword" placeholder="Password.." required>
                                        <i class="toggle-password far fa-eye" id="toggleRPassword"></i>
                                        <span id="infoRPassword"></span>
                                    </div>
                                </div>

                                <!--SUBMIT BUTTON-->
                                <div>
                                    <button type="submit" class="button btn bg-cyan text-white border-cyan px-4 py-2">Submit</button>
                                </div>
                                <p class="card-text p-2">
                                    <small class="form-text text-muted">
                                        Already have an account? <a href="<c:url value="/login"/>" >Login</a>
                                    </small>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value='/js/toggle_password.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/toggle_repeat_password.js'/>"></script>
    <%@include file="/html/footer.html"%>
</body>
</html>