
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="description" content="ESN Padova application">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <title>Search User</title>
        <%@ include file="../html/favicon.html"%>
        <%@ include file="../html/cdn.html"%>
    </head>

    <body>
        <%@include file="navbar.jsp"%>

        <div class="container">
            <div class="row justify-content-center my-4">
                <div class="col-md-6">
                    <div class="card text-center border-orange">
                        <h2 class="card-title bg-orange color-white p-4">Search User</h2>

                        <div class="card-body">
                            <form method="POST" action="<c:url value="/search-users"/>">
                                <%--ID--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="userId" class="mb-2 text-left">Insert ID</label>
                                    </div>
                                    <div class="input-container">
                                        <input id="userId" name="userId" type="text" class="form-control" placeholder="ID.."/>
                                    </div>
                                </div>
                                <%--Name--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="userName" class="mb-2 text-left">Insert Name</label>
                                    </div>
                                    <div class="input-container">
                                        <input id="userName" maxlength="50" name="userName" type="text" class="form-control" placeholder="Name.."/>
                                    </div>
                                </div>
                                <%--Surname--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="userSurname" class="mb-2 text-left">Insert Surname</label>
                                    </div>
                                    <div class="input-container">
                                        <input id="userSurname" maxlength="50" name="userSurname" type="text" class="form-control" placeholder="Surname.."/>
                                    </div>
                                </div>
                                <%--CardID--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="userCardId" class="mb-2 text-left">Insert CardID</label>
                                    </div>
                                    <div class="input-container">
                                        <input id="userCardId" maxlength="50" name="userCardId" type="text" class="form-control" placeholder="CardID.."/>
                                    </div>
                                </div>
                                <%--Email--%>
                                <div class="mx-2 my-2">
                                    <div class="d-flex justify-content-start">
                                        <label for="userEmail" class="mb-2 text-left">Insert Email</label>
                                    </div>
                                    <div class="input-container">
                                        <input id="userEmail" maxlength="254" name="userEmail" type="text" class="form-control" placeholder="Email.."/>
                                    </div>
                                </div>

                                <%--Button--%>
                                <div class="row d-flex my-4">
                                    <div class="pb-3">
                                        <button type="submit" class="button bg-orange text-white border-orange px-4 py-2">Submit</button>
                                    </div>
                                    <div>
                                        <button type="reset" class="button bg-orange text-white border-orange px-4 py-2">Reset the form</button>
                                    </div>
                                </div>
                            </form>
                            <div style="border-top: solid 1px black;" class="py-4">
                                <form method="POST" action="<c:url value="/search-users"/>">
                                    <input name="userId" type="hidden" value=""/>
                                    <input name="userName" type="hidden" value=""/>
                                    <input name="userSurname" type="hidden" value=""/>
                                    <input name="userCardId" type="hidden" value=""/>
                                    <input name="userEmail" type="hidden" value=""/>
                                    <button type="submit" class="button bg-orange text-white border-orange px-4 py-2">Show all users</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/html/footer.html"%>
    </body>
</html>
