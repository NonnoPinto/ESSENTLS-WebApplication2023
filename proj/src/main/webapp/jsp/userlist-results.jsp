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
    <meta name="description" content="ESN Padova application">
    <meta name="viewport">
    <title>List of found Users</title>
</head>

<body>
<div class="navbar"><%@include file="navbar.jsp"%></div>

<div class="title">
  <h1>List of found Users</h1>
  <hr/>
</div>

<!-- display the list of found users, if any -->
<c:if test='${not empty userList}'>
  <table>
    <thead>
    <tr>
      <th>id</th><th>name</th><th>surname</th><th>sex</th><th>dateOfBirth</th><th>cardId</th><th>tier</th><th>edit</th>
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
        <td>
          <form method="POST" action="<c:url value="/select-user-by-id"/>">
            <input type="hidden" name="userId" value="${user.getId()}"/>
            <button type="submit">Edit</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
</body>
</html>