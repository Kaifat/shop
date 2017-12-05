<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


	<div class="center">
	
	    <ul class="cart-list">
	    <c:forEach items="${cartItems}" var="cartItem">
	        <li class="cart-item">
	        	<h3>${cartItem.getProduct().title}</h3>
	        	<img alt="${cartItem.getProduct().title}" 
	        		src="data:${cartItem.getProduct().imageMimeType};base64,${cartItem.getProduct().imageString}">
	            <h3>${cartItem.price}</h3>
	            <a href="/cart?delete&cartItemId=${cartItem.id}" class="btn btn-danger" role="button">Remove</a>
	        </li>
	    </c:forEach>
	    </ul>
	
	    <div class="action-box">
	        <a href="/products" class="btn btn-info" role="button">Back to products</a>
	    </div>
	
	</div>
