<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="row">
	<div class="col-12">
		<div class="text-center">
				    <h2 class="text-center">Orders List</h2>
			<div class="panel-body">
				<c:forEach items="${orderList}" var="order">
					<div class="row order" id="order-${order.getId()}">
						<div class="col-1">
						# ${order.getId()}
						</div>
						<div class="col-1">
							<h6>
								${order.getStatus()}
								</h6>
						</div>
						<div class="col-3">
							<h6>
								<strong>${order.getUser().getLastName()} 
										${order.getUser().getFirstName()}
										<span class="text-muted"> (ID # ${order.getUser().getId()})</span>
								</strong>
							</h6>
						</div>
						<div class="col-2">
							<h6>
								${order.getAddress()} 
							</h6>
						</div>
						<div class="col-2">
							<h6>
								${order.getPhone()} 
							</h6>
						</div>
						<div class="col-2">
							<h6>
								${order.getEmail()} 
							</h6>
						</div>
						<div class="col-1">
						<a href="/order/details/${order.getId()}" class="btn btn-primary" role="button">View</a>
						</div>
								
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
