<h1>Navbar</h1>

<a rel="noopener noreferrer nofollow" href="<c:url value='/home'/>">home</a>
<a rel="noopener noreferrer nofollow" href="<c:url value='/signup'/>">Sign up</a>
<c:if test="${sessionScope.userId == null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/login'/>">login</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/register'/>">register</a>
</c:if>
<c:if test="${sessionScope.userId != null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/profile'/>">profile</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/logout'/>">logout</a>
</c:if>
<c:if test="${sessionScope.userId != null}">
    <a rel="noopener noreferrer nofollow" href="<c:url value='/edit-user'/>">Edit user</a>
    <a rel="noopener noreferrer nofollow" href="<c:url value='/create-event'/>">Admin Create event</a>
</c:if>