<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="description" content="ESN Padova application">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="ISO-8859-1">
  <title>Cause Create</title>
  <%@ include file="../html/favicon.html"%>
  <%@ include file="../jsp/cdn.jsp"%></head>

<body>
  <%@include file="navbar.jsp"%>

  <div class="container">
    <div class="row justify-content-center my-4">
      <div class="col-md-6">
        <div class="card text-center border-magenta">
          <h1 class="card-title bg-magenta color-white p-4">Create Cause</h1>

          <div class="card-body">
            <!--Cause Name-->
            <div class="form-group mb-4">
							<div class="d-flex justify-content-start">
								<label for="causeName" class="mb-2 text-left">Insert Cause Name</label>
							</div>
							<div class="input-container">
								<input class="form-control" type="text" name="causeName" id="causeName" placeholder="Cause name.." maxlength="255" required>
							</div>
						</div>
            <!--SUBMIT-->
            <div>
				<button type="submit" id="ajaxButton" class="button btn bg-magenta text-white border-magenta px-4 py-2">Submit</button>
			</div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="results" class="text-muted text-center">
  </div>
  <script type="text/javascript" src="<c:url value="/js/ajax_cause_create.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/js/enter_submit.js"/>"></script>
  <%@include file="/html/footer.html"%>
</body>
</html>
