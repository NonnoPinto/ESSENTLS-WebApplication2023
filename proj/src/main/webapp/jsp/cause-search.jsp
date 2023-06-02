<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Search</title>
  <%@ include file="../html/favicon.html"%>
  <%@ include file="../html/cdn.html"%></head>

<body>
  <%@include file="navbar.jsp"%>

  <div class="container">
    <div class="row justify-content-center my-4">
      <div class="col-md-10 col-lg-8 col-xl-6">
        <div class="card text-center border-green">
          <h2 class="card-title bg-green color-white p-4">Search Cause</h2>
          <div class="card-body">

            <!--ID-->
            <div class="form-group mb-4">
              <div class="d-flex justify-content-start">
                <label for="causeId" class="mb-2 text-left">Insert ID</label>
              </div>
              <div class="input-container">
                <input class="form-control" type="text" name="causeId" id="causeId" placeholder="ID.." maxlength="255" required>
              </div>
            </div>

            <!--Part of-->
            <div class="form-group mb-4">
              <div class="d-flex justify-content-start">
                <label for="subCause" class="mb-2 text-left">Insert (part of) Cause, empty for all causes</label>
              </div>
              <div class="input-container">
                <input class="form-control" type="text" name="subCause" id="subCause" placeholder="Sub-Cause.." maxlength="255" required>
              </div>
            </div>

            <!--SUBMIT BUTTON-->
            <div class="my-2">
              <button type="submit" id="ajaxButton" class="button bg-green text-white border-green px-4 py-2">Search</button>
            </div>
            <div class="my-2">
              <button type="submit" id="ajaxButtonAll" class="button bg-white border-green color-green px-4 py-2">Show all Causes</button>
            </div>
        </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center my-4">
      <div id="instructions">
      </div>
    </div>
    <div class="row justify-content-center my-4">
      <div id="results">
      </div>
    </div>
  </div>
  <script type="text/javascript" src="<c:url value="/js/ajax_causes.js"/>"></script>
  <%@include file="/html/footer.html"%>
</body>
</html>
