<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <link rel="stylesheet" href="${resourceContext}/layout.css">
    <link rel="stylesheet" href="${resourceContext}/style.css">
    <script type="text/javascript" src="${resourceContext}/bootstrap.bundle.min.js"></script>
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
