<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col-12 offset-0 col-md-6 offset-md-3 col-lg-6 offset-lg-3">
		<h2 class="text-center">
			${form_title}
		</h2>
							
		<form:form modelAttribute="product" enctype="multipart/form-data">
			<div class="form-group">
				<form:label path="title">Title:</form:label>
				<form:input class="form-control" path="title" />
			</div>

			<div class="form-group">
				<form:label path="title">Description:</form:label>
				<form:input class="form-control" path="description" />
			</div>
			<div class="form-group">
				<form:label path="title">Cost:</form:label>
				<form:input class="form-control" path="cost" />
			</div>
			<div class="form-group">
				<form:label path="title">Balance:</form:label>
				<form:input class="form-control" path="balance" />
			</div>
			<div class="form-group">
				<form:label path="title">Image:</form:label>
				<input name="file" type="file" accept="image/*" />
			</div>
			<div class="form-group">
				<input type="submit" value="Save" class="btn btn-primary" />
			</div>
		</form:form>

	</div>
</div>