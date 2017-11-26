<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
	<div class="center">
	
	    <ul class="cart-list">
	    <c:forEach items="${products}" var="prod">
	        <li class="cart-item">
	            <h3>${prod.title}</h3>
	            <a href="/cart?delete&prodId=${prod.id}" class="btn btn-danger" role="button">Remove</a>
	        </li>
	    </c:forEach>
	    </ul>
	
	    <div class="action-box">
	        <a href="/products" class="btn btn-info" role="button">Back to products</a>
	    </div>
	
	</div>
	
	<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>    
	<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/js/main.js"></script>
</body>
</html>
