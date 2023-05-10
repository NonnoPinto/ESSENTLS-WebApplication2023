<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="description" content="ESN Padova application">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- @ include file="/html/cdn.html"--%>
    <meta charset="utf-8">
    <title>Register</title>
</head>
<body>
    <%@include file="navbar.jsp"%>

    <div class="container">
        <div class="row justify-content-center my-4">
            <div class="col-md-6">
                <div class="card text-center border-orange">
                    <h2 class="card-title bg-orange color-white p-4"> Register</h2>
                
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

                        <form method="POST" action="<c:url value="/register"/>" enctype="multipart/form-data">
                            <div>
                                <!--EMAIL-->
                                <div class="form-group my-4">
                                    <div class="d-flex justify-content-start">
                                        <label label for="email" class="mb-2 text-left">Insert your Email</label>
                                    </div>  
                                    <div class="input-container">
                                        <input name="email" id="email" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$" type="email" class="form-control" placeholder="Email.." required/>
                                        <span id="infoEmail"></span>
                                    </div>
                                </div>

                                <!--PASSWORD-->
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

                                <!--RE-PASSWORD-->
                                <div class="form-group my-4">
                                    <div class="d-flex justify-content-start">
                                        <label for="rpassword" class="mb-2 text-left">Repeat Password</label>
                                    </div>
                                    <div class="input-container">
                                        <input class="form-control" type="password" name="rpassword" id="rpassword" placeholder="Password.." required>
                                        <i class="togglePassword far fa-eye" id="toggleRPassword"></i>
                                        <span id="infoRPassword"></span>
                                    </div>
                                </div>

                                <!--SUBMIT BUTTON-->
                                <div>
                                    <button type="submit" class="button bg-orange text-white border-orange px-4 py-2">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<c:url value="/js/toggle_password.js"/>"></script>
    <%@include file="/html/footer.html"%>
</body>
</html> 