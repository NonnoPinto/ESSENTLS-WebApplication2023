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
  <div>
    <img src="${event.thumbnail}"/>
    <h2>${event.name}</h2>
    <hr>
    <p>
      ${event.description}
    </p>
    <p>Cost: ${event.price}</p>
    <p>Current participants: ${nParticipants}</p>
    <p>Waiting List: ${nWaiting}</p>
  </div>


</div>

</body>
</html>