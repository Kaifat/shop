<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/">Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
            	<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
					<a href="/profile" class="nav-link">Profile</a>
				</sec:authorize>
            </li>
            <li class="nav-item">
            	<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="/product" class="nav-link" role="button">New product</a>
				</sec:authorize>
            </li>
            <li class="nav-item">
              <a href="/order" class="nav-link" role="button">Cart</a>
            </li>
            <li class="nav-item">
            	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
					<a href="/register" class="nav-link">Registration</a>
				</sec:authorize>
            </li>
            <li class="nav-item">
            	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
					<a href="/login" class="nav-link">Login</a>
				</sec:authorize>
            	<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
					<a href="/logout" class="nav-link">Logout</a>
				</sec:authorize>
            </li>
          </ul>
        </div>
      </div>
    </nav>
