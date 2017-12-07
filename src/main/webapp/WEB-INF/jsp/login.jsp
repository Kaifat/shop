<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	<form name="f" th:action="@{/login}" method="post">
		<fieldset>
		    <legend>Please Login</legend>
		    <div th:if="${param.error}" class="alert alert-error">
		    	Invalid username and password.
			</div>
			<div th:if="${param.logout}" class="alert alert-success">
		        You have been logged out.
		    </div>
		    <div class="form-group">
			    <label for="username">Username</label>
			    <input class="form-control" type="text" id="username" name="username"/>
			    <label for="password">Password</label>
			    <input class="form-control" type="password" id="password" name="password"/>
			    <div class="form-actions">
			        <button type="submit" class="btn">Log in</button>
			    </div>
		   	</div>
		</fieldset>
	</form>
