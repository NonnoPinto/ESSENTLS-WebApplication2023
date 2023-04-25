<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>ESN</title>
    </head>
    <body>
        <div class="container">
            <img src="https://upload.wikimedia.org/wikipedia/commons/2/20/ESN-logo.png"><br/><br/>
            Welcome to ESN!

            <a href="<c:url value='/login'/>"><button>Sign in</button></a>
            <a href="<c:url value='/signup'/>"><button>Sign up</button></a>
        </div>
    </body>
</html>