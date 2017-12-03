<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
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
    </div>
