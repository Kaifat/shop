<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="resourceContext" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="${resourceContext}/bootstrap.min.css">
    <link rel="stylesheet" href="${resourceContext}/layout.css">
    <link rel="stylesheet" href="${resourceContext}/style.css">
    <script type="text/javascript" src="${resourceContext}/jquery-3.2.1.min.js"></script>
    
</head>

<body>

    <form:form modelAttribute="searchForm" cssClass="search-box">
        <form:input path="searchText"/>
        <input type="submit" class="btn btn-success" value="Search"/>
    </form:form>

    <ul class="prod-list">
	    <c:forEach items="${products}" var="prod">
	        <li id="product_${prod.id}">
	            <h3>${prod.title} ( ${prod.balance} )</h3>
	            <img alt="${prod.title}" src="data:${prod.imageMimeType};base64,${prod.imageString}">
	            <p>${prod.description}</p>
	            
	            <div class="action-box">
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
                        <a href="/order?prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/product/${prod.id}" class="btn btn-warning" role="button">Edit</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <button class="btn btn-info product_delete" type="submit">Delete</button>
                    </sec:authorize>
                </div>
	           
	        </li>
	    </c:forEach>
    </ul>

     <div class="action-box">
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/product" class="btn btn-warning" role="button">New product</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <a href="/login" class="btn btn-warning" role="button">Login</a>
            <a href="/register" class="btn btn-warning" role="button">Registration</a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
            <a href="/logout" class="btn btn-warning" role="button">Logout</a>
        </sec:authorize>
    </div>

	<script type="text/javascript" src="${resourceContext}/main.js"></script>

</body>
</html>
