<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="action-box">
	  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <a href="/login" class="btn btn-warning" role="button">Login</a>
            <a href="/register" class="btn btn-warning" role="button">Registration</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
            <a href="/logout" class="btn btn-warning" role="button">Logout</a>
        </sec:authorize>
</div>