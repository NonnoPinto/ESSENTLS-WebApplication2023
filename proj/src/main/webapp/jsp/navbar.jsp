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
    <a rel="noopener noreferrer nofollow" href="<c:url value='/edit-user'/>">Edit user</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">Logout</a>
</c:if>
<c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/create-event'/>">(Admin) Create event</a>
</c:if>
<c:if test="${sessionScope.sessionUserId != null && sessionScope.sessionUserTier > 2}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/search-users'/>">(Admin) Search User</a>
</c:if>