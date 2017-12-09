<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


	<div class="center">
	
	    <ul class="cart-list">
	    <c:forEach items="${cartItems}" var="cartItem">
	        <li class="cart-item">
	        	<h3>${cartItem.getProduct().title}</h3>
	        	<img alt="${cartItem.getProduct().title}" 
	        		src="data:${cartItem.getProduct().imageMimeType};base64,${cartItem.getProduct().imageString}">
	            <h3>${cartItem.price}</h3>
	            <h3>${cartItem.amount}</h3>
	            <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
            		<a href="/cart?delete&itemId=${cartItem.id}" class="btn btn-danger" role="button">Remove</a>
        		</sec:authorize>
        		<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
	            	<a href="/cart?delete&itemId=${cartItem.product.id}" class="btn btn-danger" role="button">Remove</a>
            	</sec:authorize>
	        </li>
	    </c:forEach>
	    </ul>
	
	    <div class="action-box">
	        <a href="/products" class="btn btn-info" role="button">Back to products</a>
	        <a href="/cart?buy" class="btn btn-info" role="button">Buy</a>
	        
	    </div>
	    <div>
	    <h3>Total Count:</h3>
			  ${totalCount}  
	    </div>
	</div>
