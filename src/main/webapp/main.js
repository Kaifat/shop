$(document).ready(function(){

	$(document).on("click","button.product_delete",function() {
		
		var productRow =  $(this).parents("li");
		console.log(productRow);
		var productId = productRow.attr('id');
		productId = productId.match(/\d+/);
		
		$.ajax({
			type : "DELETE",
			url : window.location.protocol + '//' + window.location.host + "/product/" + productId,
			success: function(resultMsg){
				productRow.remove();
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});
	
	
	
	$('#password, #confirm_password').on('keyup', function () {
		  if ($('#password').val() == $('#confirm_password').val()) {
		    $('#message').html('Matching').css('color', 'green');
		  } else 
		    $('#message').html('Not Matching').css('color', 'red');
		});
	
	
	
	
});
