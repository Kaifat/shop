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

	<script src="/js/main.js"></script>

</body>
</html>
