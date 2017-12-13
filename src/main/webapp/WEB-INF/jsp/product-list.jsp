<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
  	<div class="row text-center">
  		<div class="col-12 offset-0 col-md-12 offset-md-0 col-lg-6 offset-lg-3">
	    <form:form modelAttribute="searchForm">
	    	<div class="input-group">
	        	<form:input path="searchText" class="form-control" placeholder="Search..." />
	        	<span class="input-group-btn">
			        <button class="btn btn-primary" type="submit">Go!</button>
			    </span>
	        </div>
	    </form:form>
	    </div>
   </div>
   <div class="row text-center">
   		<div class="col-12 offset-0 col-md-12 offset-md-0 col-lg-6 offset-lg-3">
   			<c:if test="${SUCCESS_MESSAGE != null}">
				<div class="alert alert-success">${SUCCESS_MESSAGE}</div>
			</c:if>
   		</div>
   </div>

	<!-- Page Features -->
	
      <div class="row text-center">
      
      <sec:authorize access="hasRole('ROLE_ADMIN')">
     	<c:forEach items="${products}" var="prod">
	        <div class="product-item col-lg-3 col-md-6 mb-4" id="product_${prod.id}">
	          <div class="card">
	            <img class="card-img-top" alt="${prod.title}" 
	            	src="data:${prod.imageMimeType};base64,${prod.imageString}">
	            <div class="card-body">
	              <h4 class="card-title">${prod.title} (${prod.balance})</h4>
	              <h4>\$ ${prod.cost}</h4>
	              <p class="card-text">${prod.description}</p>
	            </div>
	            <div class="card-footer">                   
                    <a href="/product/${prod.id}" class="btn btn-warning" role="button">Edit</a>
                    <button class="btn btn-danger product_delete" type="button">Delete</button>	                    
	            </div>
          	  </div>
          	</div>
         </c:forEach>
      </sec:authorize>
      
	
	  <sec:authorize access="!hasRole('ROLE_ADMIN')">
		<c:forEach items="${products}" var="prod">
			<c:if test = "${prod.balance > 0}">
		        <div class="product-item col-lg-3 col-md-6 mb-4" id="product_${prod.id}">
		          <div class="card">
		            <img class="card-img-top" alt="${prod.title}" 
		            	src="data:${prod.imageMimeType};base64,${prod.imageString}">
		            <div class="card-body">
		              <h4 class="card-title">${prod.title} (${prod.balance})</h4>
		              <h4>\$ ${prod.cost}</h4>
		              <p class="card-text">${prod.description}</p>
		            </div>
		            <div class="card-footer">
		              	<button class="btn btn-primary add-to-cart" type="button">Add to Cart</button>
		              	<button class="btn btn-success buy-product" type="button">Buy</button>	                    
		            </div>
	          	  </div>
	          	</div>
          	</c:if>
         </c:forEach>
      </sec:authorize>
     
      </div>
      <!-- /.row -->
