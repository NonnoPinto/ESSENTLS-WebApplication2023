<h1>Navbar</h1>

<a rel="noopener noreferrer nofollow" href="<c:url value='/home'/>">Home</a>
<c:if test="${sessionScope.userId == null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/login'/>">Login</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/signup'/>">Sign up</a>
</c:if>
<c:if test="${sessionScope.userId != null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/profile'/>">Profile</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/joined-events'/>">My Events</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">Logout</a>
</c:if>
<!-- TODO: change condition, if user is admin -->
<c:if test="${sessionScope.userId != null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/edit-user'/>">Edit user</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/create-event'/>">(Admin) Create event</a>
</c:if>