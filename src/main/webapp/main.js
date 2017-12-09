$(document).ready(function(){

	$(document).on("click","button.product_delete",function() {
		
		var productRow =  $(this).parents(".product-item");
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
