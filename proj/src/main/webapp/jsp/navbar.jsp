<div class="d-block p-2 skippy"></div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value='/'/>"><img src="./media/esn_padova_logo_full_color_2.png" class="top-logo-navbar" alt="Logo of ESN Padova"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav"><!-- EMPTY --></ul>
            <ul class="navbar-nav ml-auto w-100 justify-content-center">
                <c:if test="${sessionScope.sessionUserTier == 0}">
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="<c:url value='/membership'/>">Complete your membership</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null}">
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="<c:url value='/'/>">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="<c:url value='/joined-events'/>">My Events</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="https://padova.esn.it/">Padova</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="https://padova.esn.it/en/esncard">ESNCard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active text-nowrap" rel="noopener noreferrer nofollow" href="https://padova.esn.it/en/association">About Us</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
                    <li class="nav-item dropdown admin">
                        <a rel="noopener noreferrer nofollow" class="nav-link dropdown-toggle text-nowrap" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Admin Manage
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="<c:url value='/create-event'/>">Create event</a></li>
                            <li><a class="dropdown-item" href="<c:url value='/search-users'/>">Search user</a></li>
                            <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier >= 4}">
                                <li><a a class="dropdown-item" href="<c:url value='/cause-create'/>">Create cause</a></li>
                                <li><a a class="dropdown-item" href="<c:url value='/cause-search'/>">Search cause</a></li>
                                <li><a a class="dropdown-item" href="<c:url value='/tag-search'/>">Search tag</a></li>
                                <li><a a class="dropdown-item" href="<c:url value='/select-user-by-id'/>">Edit user (select by id)</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ms-auto d-flex flex-row">
                <c:if test="${sessionScope.sessionUserId == null}">
                    <li class="nav-item text-nowrap">
                        <a class="btn btn-outline-primary" href="<c:url value='/login'/>">Login</a>
                    </li>
                    <li class="nav-item text-nowrap">
                        <a class="btn btn-primary" href="<c:url value='/signup'/>">Sign up</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUserId != null}">
                    <li class="nav-item dropdown">
                        <a rel="noopener noreferrer nofollow" class="nav-link dropdown" href="#" id="navbarDropdownProfile" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownProfile">
                            <li><a class="dropdown-item" href="<c:url value='/profile'/>">Profile Info</a></li>
                            <li><a class="dropdown-item" rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">Logout</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>