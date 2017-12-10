<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
	<head>
	    <title><tiles:getAsString name="title"/></title>
	    
	    <!-- Bootstrap core CSS -->
    	<link rel="stylesheet" href="${resourceContext}/vendor/bootstrap/css/bootstrap.min.css">

    	<!-- Custom styles for this template -->
	    <link rel="stylesheet" href="${resourceContext}/layout.css">
	    <link rel="stylesheet" href="${resourceContext}/style.css">
	    
	</head>

	<body>
		<tiles:insertAttribute name="header" />
		<div class="container">
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	 
	<!-- Bootstrap core JavaScript -->
    <script src="${resourceContext}/vendor/jquery/jquery.min.js"></script>
    <script src="${resourceContext}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>  
	<script src="${resourceContext}/main.js"></script>
	    
  	</body>
</html>