<div class="navbar">
    <a href="<c:url value='/'/>">
        <img src="./media/esn_padova_logo_full_color_2.png" class="top-logo-navbar" alt="Logo of ESN Padova"">
    </a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/home'/>"><h5>Home</a></h5>
    <c:if test="${sessionScope.sessionUserId == null}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/login'/>"><h5>Login</a></h5>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/signup'/>"><h5>Sign up</a></h5>
    </c:if>
    <c:if test="${sessionScope.sessionUserTier == 0}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/membership'/>"><h5>Complete your membership</a></h5>
    </c:if>
    <c:if test="${sessionScope.sessionUserId != null}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/profile'/>"><h5>Profile</a></h5>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/joined-events'/>"><h5>My Events</a></h5>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>"><h5>Logout</a></h5>
    </c:if>
    <br/>
    <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/create-event'/>"><h5>(Admin) Create event</a></h5>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/search-users'/>"><h5>(Admin) Search User</a></h5>
    </c:if>
    <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier >= 4}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/cause-create'/>"><h5>(Admin) Create cause</a></h5>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/cause-search'/>"><h5>(Admin) Search cause</a></h5>
    </c:if>
</div>
<hr>