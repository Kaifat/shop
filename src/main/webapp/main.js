$(document).ready(
		function() {

			$(document).on(
					"click",
					"button.product_delete",
					function() {

						var productRow = $(this).parents(".product-item");
						var productId = productRow.attr('id');
						productId = productId.match(/\d+/);

						$.ajax({
							type : "DELETE",
							url : window.location.protocol + '//'
									+ window.location.host + "/product/"
									+ productId,
							success : function(resultMsg) {
								productRow.remove();
							},
							error: function(jqXhr, textStatus, errorThrown){
						        console.log(errorThrown);
							}
						});
					});

			$(document).on(
					"click",
					"button.add-to-cart",
					function() {

						var productRow = $(this).parents(".product-item");
						var productId = productRow.attr('id');
						var res = productId.match(/\d+/);
						
						if (res.length) {
							var postData = {"prodId": res[0]};
	
							$.ajax({
								type : "POST",
								url : window.location.protocol + '//'
									+ window.location.host + "/order/add",
								contentType: 'application/json',
							    data: JSON.stringify(postData),
								success : function(resultMsg) {
									console.log( resultMsg );
								},
							    error: function(jqXhr, textStatus, errorThrown){
							        console.log(errorThrown);
							    }
							});
						}
					});
			
			$(document).on(
					"click",
					"button.delete-from-cart",
					function() {

						var productRow = $(this).parents(".order-item");
						var productId = productRow.attr('id');
						var res = productId.match(/\d+/);
						
						if (res.length) {
							var postData = {"id": res[0]};

							$.ajax({
								type : "DELETE",
								url : window.location.protocol + '//'
										+ window.location.host + "/order/delete-order-item",
								contentType: 'application/json',
							    data: JSON.stringify(postData),
								success : function(resultMsg) {
									productRow.remove();
								},
								error: function(jqXhr, textStatus, errorThrown){
							        console.log(errorThrown);
								}
							});
						}
					});
			
			

			$('#password, #confirm_password').on('keyup', function() {
				if ($('#password').val() == $('#confirm_password').val()) {
					$('#registerButton').attr('disabled', false);
					if ($('#registerButton').hasClass('disabled')) {
						$('#registerButton').removeClass('disabled');
					}
					$('#message').html('Matching').css('color', 'green');
					console.log("suc");
				} else {
					$('#registerButton').attr('disabled', true);
					if (!$('#registerButton').hasClass('disabled')) {
						$('#registerButton').addClass('disabled');
					}
					$('#message').html('Not Matching').css('color', 'red');
					console.log("fail");
				}
			});

		});
