<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
  	<div class="row text-center">
  		<div class="col-12 offset-0 col-md-6 offset-md-3 col-lg-6 offset-lg-3">
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

	<!-- Page Features -->
      <div class="row text-center">
	
		<c:forEach items="${products}" var="prod">
	        <div class="product-item col-lg-3 col-md-6 mb-4" id="product_${prod.id}">
	          <div class="card">
	            <img class="card-img-top" alt="${prod.title}" 
	            	src="data:${prod.imageMimeType};base64,${prod.imageString}">
	            <div class="card-body">
	              <h4 class="card-title">${prod.title} (${prod.balance})</h4>
	              <p class="card-text">${prod.description}</p>
	            </div>
	            <div class="card-footer">

                   <sec:authorize access="hasRole('ROLE_ADMIN')">
                       <button class="btn btn-success product_restore" type="button">Restore</button>
                   </sec:authorize>
	            </div>
	          </div>
	        </div>
         </c:forEach>
       </div>
       <!-- /.row -->
