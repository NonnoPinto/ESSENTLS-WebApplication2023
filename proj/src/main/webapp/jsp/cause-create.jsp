<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Create</title>
</head>

<body>
  <%@include file="navbar.jsp"%>

  <div class="containter">
    <div class="row justify-content-center my-4">
      <div class="col-md-6">
        <div class="card text-center border-orange">
          <h2 class="card-title bg-orange color-white p-4">Create Cause</h2>

          <div class="card-body">
            <!--Cause Name-->
            <div class="form-group mb-4">
							<div class="d-flex justify-content-start">
								<label for="causeName" class="mb-2 text-left">Insert Cause Name</label>
							</div>
							<div class="input-container">
								<input class="form-control" type="text" name="causeName" id="causeName" placeholder="Cause name.." required>
							</div>
						</div>

            <!--SUBMIT-->
            <div>
							<button type="submit" id="ajaxButton" class="button bg-orange text-white border-orange px-4 py-2">Submit</button>
						</div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="results" class="text-muted text-center">
  </div>
  <script type="text/javascript" src="<c:url value="/js/ajax_cause_create.js"/>"></script>
</body>
</html>
