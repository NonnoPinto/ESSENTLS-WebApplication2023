<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Search</title>
</head>

<body>
  <%@include file="navbar.jsp"%>

  <div class="container">
    <div class="row justify-content-center my-4">
      <div class="col-md-6">
        <div class="card text-center border-orange">
          <h2 class="card-title bg-orange color-white p-4">Search Cause</h2>
          <div class="card-body">

            <!--ID-->
            <div class="form-group mb-4">
              <div class="d-flex justify-content-start">
                <label for="causeId" class="mb-2 text-left">Insert ID</label>
              </div>
              <div class="input-container">
                <input class="form-control" type="text" name="causeId" id="causeId" placeholder="ID.." required>
              </div>
            </div>

            <!--Part of-->
            <div class="form-group mb-4">
              <div class="d-flex justify-content-start">
                <label for="subCause" class="mb-2 text-left">Insert (part of) Cause, empty for all causes</label>
              </div>
              <div class="input-container">
                <input class="form-control" type="text" name="subCause" id="subCause" placeholder="Sub-Cause.." required>
              </div>
            </div>

            <!--SUBMIT BUTTON-->
          <div>
            <button type="submit" id="ajaxButton" class="button bg-orange text-white border-orange px-4 py-2">Search</button>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
  <div id="results">
  </div>
  <script type="text/javascript" src="<c:url value="/js/ajax_causes.js"/>"></script>
  <%@include file="/html/footer.html"%>
</body>
</html>
