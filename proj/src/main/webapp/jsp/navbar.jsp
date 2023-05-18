<%@ include file="../html/cdn.html"%>
<!DOCTYPE html>
<head>
	<title>Navbar</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value='/'/>"><img src="./media/esn_padova_logo_full_color_2.png" class="top-logo-navbar" alt="Logo of ESN Padova"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" id="collapse">
            <ul class="navbar-nav mr-auto">
                <c:if test="${sessionScope.sessionUserTier == 0}">
                    <li class="nav-item">
                        <a class="nav-link active" rel="noopener noreferrer nofollow" href="<c:url value='/membership'/>">Complete your membership</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null}">
                    <li class="nav-item">
                        <a class="nav-link active" rel="noopener noreferrer nofollow" href="<c:url value='/joined-events'/>">My Events</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
                    <li class="nav-item dropdown">
                        <a rel="noopener noreferrer nofollow" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Admin Manage
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="<c:url value='/create-event'/>">Create event</a></li>
                            <li><a class="dropdown-item" href="<c:url value='/search-users'/>">Search user</a></li>
                            <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier >= 4}">
                                <li><a a class="dropdown-item" href="<c:url value='/cause-create'/>">Create cause</a></li>
                                <li><a a class="dropdown-item" href="<c:url value='/cause-search'/>">Search cause</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
            <ul class="navbar-nav ml-auto">
                <c:if test="${sessionScope.sessionUserId == null}">
                    <li class="nav-item">
                        <a class="btn btn-outline-primary" href="<c:url value='/login'/>">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href="<c:url value='/signup'/>">Sign up</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null}">
                    <li class="nav-item">
                        <a class="nav-link active" rel="noopener noreferrer nofollow" href="<c:url value='/profile'/>"><i class="bi bi-person-circle"></i> Profile</a>
                    </li>
                    <a class="nav-link active" rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">Logout</a>
                </c:if>
            </ul>
        </div>
    </div>
</nav>