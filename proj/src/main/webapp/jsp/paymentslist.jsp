<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19/4/2023
  Time: 12:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Payments list</title>
        <%@ include file="../html/favicon.html"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container">
            <div class="page-title">
                <h1>Payments List</h1>
            </div>
            <div class="row justify-content-center my-4">
                <div class="col">
                    <div class="card text-center border-orange">
                        <div class="table-responsive">
                            <table class="table table-hover mh-75 align-middle">
                                <thead>
                                    <tr>
                                        <th class="py-3 px-4" scope="col">UserId</th>
                                        <th class="py-3 px-4" scope="col">EventId</th>
                                        <th class="py-3" scope="col">PaymentMethod</th>
                                        <th class="py-3" scope="col">Amount</th>
                                        <th class="py-3" scope="col">Date</th>
                                        <th class="py-3" scope="col">Note</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="payment" items="${Payments}">
                                        <tr>
                                            <td class="px-4"><c:out value="${payment.getUserId()}"/></td>
                                            <td class="px-4"><c:out value="${payment.getEventId()}"/></td>
                                            <td><c:out value="${payment.getMethod()}"/></td>
                                            <td><c:out value="${payment.getAmount()}"/></td>
                                            <td><c:out value="${payment.getDate()}"/></td>
                                            <td><c:out value="${payment.getNotes()}"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>
