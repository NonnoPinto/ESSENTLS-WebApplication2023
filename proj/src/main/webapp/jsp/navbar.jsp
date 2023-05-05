<div class="navbar">
    <a href="<c:url value='/'/>">
        <img src="https://padova.esn.it/sites/esnpadova.it/files/esn_padova_logo_full_color_2.png"
        alt="Logo of ESN Padova" style="max-width: 200px; height: auto;">
    </a>
    <h3>Navbar</h3>
    
    <a rel="noopener noreferrer nofollow" href="<c:url value='/home'/>">Home</a>
    <c:if test="${sessionScope.sessionUserId == null}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/login'/>">Login</a>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/signup'/>">Sign up</a>
    </c:if>
    <c:if test="${sessionScope.sessionUserTier == 0}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/membership'/>">Complete your membership</a>
    </c:if>
    <c:if test="${sessionScope.sessionUserId != null}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/profile'/>">Profile</a>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/joined-events'/>">My Events</a>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">Logout</a>
    </c:if>
    <br/>
    <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/create-event'/>">(Admin) Create event</a>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/search-users'/>">(Admin) Search User</a>
    </c:if>
    <c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier >= 4}">
        <a rel="noopener noreferrer nofollow" href="<c:url value='/cause-create'/>">(Admin) Create cause</a>
        <a rel="noopener noreferrer nofollow" href="<c:url value='/cause-search'/>">(Admin) Search cause</a>
    </c:if>
</div>
<hr>