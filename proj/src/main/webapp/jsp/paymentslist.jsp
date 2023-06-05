<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="ISO-8859-1">
        <title>Payments list</title>
        <%@ include file="../html/favicon.html"%>
        <%@ include file="../jsp/cdn.jsp"%>
    </head>
    <body>
        <%@include file="navbar.jsp"%>
        <div class="container">
            <h1 class="page-title text-center p-2">User Payments List</h1>
            <div class="row justify-content-center my-4">
                <div class="col-12">
                    <div class="card text-center border-blue">
                        <div class="table-responsive">
                            <table class="table table-hover mh-75 align-middle">
                                <thead>
                                    <tr>
                                        <th class="py-3 px-4" scope="col">UserId</th>
                                        <th class="py-3 px-4" scope="col">EventId</th>
                                        <th class="py-3" scope="col">Payment Method</th>
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
            <c:if test="${requestScope['jakarta.servlet.forward.servlet_path'] == '/userpaymentslist'}">
            <div class="row justify-content-center mt-2 mb-4">
                <div class="col-xxl-10 col-12 d-flex justify-content-center">
                    <a href="<c:url value="/profile"/>">
                        <button type="button" class="button btn bg-blue border-blue color-white px-4 py-2">Back</button>
                    </a>
                </div>
            </div>
            </c:if>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>
