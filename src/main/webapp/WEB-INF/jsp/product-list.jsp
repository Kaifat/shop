<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
	<link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>

    <form:form modelAttribute="searchForm">
        <form:input path="searchText" />
        <input type="submit" value="Search" />
    </form:form>

    <ul>
    <c:forEach items="${products}" var="prod">
        <li id="product_${prod.id}">
            <h3>${prod.title} ( ${prod.balance} )</h3>
            <img alt="${prod.title}" src="data:${prod.imageMimeType};base64,${prod.imageString}">
            <p>${prod.description}</p>
            <a href="/product/${prod.id}">Edit</a>
            <a href="/cart?add&prodId=${prod.id}" class="btn btn-info" role="button">Add to Cart</a>
                <a href="/order?prodId=${prod.id}" class="btn btn-info" role="button">Buy</a>
            <button class="btn btn-info btn-lg product_delete" type="submit">Delete</button>
        </li>
    </c:forEach>
    </ul>

    <div>
        <a href="/product">New product</a>
    </div>

	<script src="/webjars/jquery/3.1.1/jquery.min.js"></script>    
	<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/js/main.js"></script>

</body>
</html>
