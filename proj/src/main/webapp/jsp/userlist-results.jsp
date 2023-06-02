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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>List of found Users</title>
  <%@ include file="../html/favicon.html"%>
  <%@ include file="../html/cdn.html"%>
</head>

<body>
<%@include file="navbar.jsp"%>
<div class="container">
  <h1 class="page-title p-2">List of found Users</h1>
  <div class="row justify-content-center my-4">
    <div class="col-md-10">
      <div class="card text-center border-orange">
        <!-- display the list of found users, if any -->
        <c:if test='${not empty userList}'>
          <div class="table-responsive">
            <table class="table table-hover mh-75 align-middle">
              <thead>
              <tr>
                <th class="py-3 px-4" scope="col">id</th>
                <th class="py-3" scope="col">name</th>
                <th class="py-3" scope="col">surname</th>
                <th class="py-3" scope="col">sex</th>
                <th class="py-3" scope="col">dateOfBirth</th>
                <th class="py-3" scope="col">cardId</th>
                <th class="py-3" scope="col">tier</th>
                <th class="py-3" scope="col"></th>
                <th class="py-3" scope="col"></th>
              </tr>
              </thead>

              <tbody>
              <c:forEach var="user" items="${userList}">
                <tr>
                  <td scope="row" class="px-4"><c:out value="${user.getId()}"/></td>
                  <td><c:out value="${user.getName()}"/></td>
                  <td><c:out value="${user.getSurname()}"/></td>
                  <td><c:out value="${user.getSex()}"/></td>
                  <td><c:out value="${user.getDateOfBirth()}"/></td>
                  <td><c:out value="${user.getCardId()}"/></td>
                  <td><c:out value="${user.getTier()}"/></td>
                  <td>
                    <form method="POST" action="<c:url value="/select-user-by-id"/>">
                      <input type="hidden" name="userId" value="${user.getId()}"/>
                      <button type="submit" class="button bg-orange text-white border-orange px-2 py-1">Edit</button>
                    </form>
                  </td>
                  <td>
                    <form method="POST" action="<c:url value="/download-user-document"/>">
                      <input type="hidden" name="userId" value="${user.getId()}"/>
                      <button type="submit" class="button bg-cyan text-white border-cyan px-2 py-1">Document</button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </c:if>
      </div>
    </div>
  </div>
</div>
<%@include file="/html/footer.html"%>
</body>
</html>