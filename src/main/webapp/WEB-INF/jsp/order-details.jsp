<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="row">
	<div class="col-12 offset-0 col-md-8 offset-md-2 col-lg-8 offset-lg-2">
			<div class="text-center">
				    <h2 class="text-center">Orders Details # ${orderId}</h2>
			<div class="panel-body">
				<c:forEach items="${orderItems}" var="orderItem">
					<div class="row order-item">
						<div class="col-3">
							<img class="img-responsive" alt="${orderItem.getProduct().title}"
								src="data:${orderItem.getProduct().imageMimeType};base64,${orderItem.getProduct().imageString}"
								height="70" width="100">
						</div>
						<div class="col-5">
							<h4 class="product-name">
								<strong>${orderItem.getProduct().title}</strong>
								
							</h4>
						</div>
						<div class="col-4">
							<div class="row">
								<div class="col-6 text-right">
									<h6>
										<strong>\$ ${orderItem.price} <span class="text-muted">*</span></strong>
									</h6>
								</div>
								<div class="col-6">
									${orderItem.amount}
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="panel-footer">
					<div class="row text-center">
						<div class="col-12">
							<h4 class="text-right">Total Amount <strong>${totalCount}</strong></h4>
							<h4 class="text-right">Total Price <strong>\$ ${totalSum}</strong></h4>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
