<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="row">
	<div class="col-12 offset-0 col-md-8 offset-md-2 col-lg-8 offset-lg-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="row">
						<div class="col-6">
							<h5>
								<span class="glyphicon glyphicon-shopping-cart"></span> Shopping
								Cart
							</h5>
						</div>
						<div class="col-6 text-right">
							<a href="/products" class="btn btn-primary" role="button"> <span
								class="glyphicon glyphicon-share-alt"></span> Continue shopping
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<c:forEach items="${cartItems}" var="cartItem">
					<div class="row order-item" id="product-
						<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
					 		${cartItem.getId()}
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
							${cartItem.getProduct().id}
						</sec:authorize>
					">
						<div class="col-3">
							<img class="img-responsive" alt="${cartItem.getProduct().title}"
								src="data:${cartItem.getProduct().imageMimeType};base64,${cartItem.getProduct().imageString}"
								height="70" width="100">
						</div>
						<div class="col-5">
							<h4 class="product-name">
								<strong>${cartItem.getProduct().title}</strong>
							</h4>
						</div>
						<div class="col-4">
							<div class="row">
								<div class="col-4 text-right">
									<h6>
										<strong>${cartItem.price} <span class="text-muted">*</span></strong>
									</h6>
								</div>
								<div class="col-5">
									<input type="number" class="form-control product-amount"
										value="${cartItem.amount}">
								</div>
								<div class="col-3">
									<button type="button" class="btn btn-danger delete-from-cart">
										x
									</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="panel-footer">
					<div class="row text-center">
						<div class="col-9">
							<h4 class="text-right">Total Amount <strong>${totalCount}</strong></h4>
							<h4 class="text-right">Total Price <strong>\$ ${totalSum}</strong></h4>
						</div>
						<div class="col-3">
							<a href="/order?buy" class="btn-block btn btn-primary" role="button">Checkout</a>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
