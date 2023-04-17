<%--
  Created by IntelliJ IDEA.
  User: Laura
  Date: 16/04/2023
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>List of found Users</title>
</head>

<body>
<h1>List of found Users</h1>
<hr/>


<!-- display the list of found users, if any -->
<c:if test='${not empty userList}'>
  <table>
    <thead>
    <tr>
      <th>id</th><th>name</th><th>surname</th><th>sex</th><th>dateOfBirth</th><th>cardId</th><th>tier</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${userList}">
      <tr>
        <td><c:out value="${user.getId()}"/></td>
        <td><c:out value="${user.getName()}"/></td>
        <td><c:out value="${user.getSurname()}"/></td>
        <td><c:out value="${user.getSex()}"/></td>
        <td><c:out value="${user.getDateOfBirth()}"/></td>
        <td><c:out value="${user.getCardId()}"/></td>
        <td><c:out value="${user.getTier()}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
</body>
</html>