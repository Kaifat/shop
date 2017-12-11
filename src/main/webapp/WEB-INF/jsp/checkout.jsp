<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-12 offset-0 col-md-6 offset-md-3 col-lg-6 offset-lg-3">
	<h2 class="text-center">Add delivery details</h2>
		<form:form method="POST" modelAttribute="deliveryDetails" class="form-horizontal">

				<div class="form-group">
					<label for="address">Address:</label>
						<form:input type="text" path="address" id="address"
							class="form-control" />
						<div class="has-error">
							<form:errors path="address" class="help-inline" />
						</div>
				</div>

				<div class="form-group">
					<label for="phone">Phone
						number:</label>
						<form:input type="text" path="phone" id="phone"
							class="form-control" />
						<div class="has-error">
							<form:errors path="phone" class="help-inline" />
						</div>
				</div>

				<div class="form-group">
					<label for="email">Email:</label>
						<form:input type="text" path="email" id="email"
							class="form-control" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
				</div>

				<div class="form-actions">
					<input type="submit" value="Complete" class="btn btn-primary" />
				</div>
		</form:form>
	</div>
</div>