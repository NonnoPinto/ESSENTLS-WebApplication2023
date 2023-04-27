<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ESN</title>
    </head>
    <body>
        <div class="container">
            <img src="https://upload.wikimedia.org/wikipedia/commons/2/20/ESN-logo.png" alt="Logo of ESN Padova"><br/><br/>
            Welcome to ESN!

            <a href="<c:url value='/login'/>"><button>Sign in</button></a>
            <a href="<c:url value='/signup'/>"><button>Sign up</button></a>
        </div>

        <footer class="footer"><%@include file="/html/footer.html"%></footer>
    </body>
</html>