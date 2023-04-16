<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Event detail</title>
</head>
<body>

<div class="container">
  <div >
    <h2>${event.name}</h2>
    <hr>
    <table>
      <c:forEach items="${participants}" var="p">
        <tr>
          <td>${p.userId}</td>
        </tr>
      </c:forEach>
    </table>
      ${event.description}
    </p>
  </div>


</div>

</body>
</html>