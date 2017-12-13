<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-12 offset-0 col-md-6 offset-md-3 col-lg-6 offset-lg-3">
		<form name="f" th:action="@{/login}" method="post">
			<div class="text-center">
				<h2 class="text-center">Please Login</h2>
				
				<c:if test="${ERROR_MESSAGE != null || param.error == 'true'}">
					<div class="alert alert-danger">
					Invalid email or password!
					</div>
				</c:if>
				<c:if test="${SUCCESS_MESSAGE != null}">
					<div class="alert alert-success">${SUCCESS_MESSAGE}</div>
				</c:if>
			</div>

			<div class="form-group">
				<label for="username">Username</label> <input class="form-control"
					type="text" id="username" name="username" />
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input class="form-control"
					type="password" id="password" name="password" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Log in</button>
			</div>
		</form>
	</div>
</div>
