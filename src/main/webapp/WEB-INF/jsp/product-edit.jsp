<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <link>
</head>
<body>

  <form:form modelAttribute="product" enctype="multipart/form-data">
        <fieldset>
            <form:label path="title">Title:</form:label>
	        <form:input path="title" />
	        <br />

            <form:label path="title">Description:</form:label>
	        <form:input path="description" />
	        <br />

            <form:label path="title">Cost:</form:label>
	        <form:input path="cost" />
	        <br />

            <form:label path="title">Balance:</form:label>
	        <form:input path="balance" />
	        <br />
	        
	         <form:label path="title">Image:</form:label>
	         <input name="file" type="file" accept="image/*" />
	        <br />
        </fieldset>

        <input type="submit" value="Save" />
    </form:form>

</body>
</html>
