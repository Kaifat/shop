package edu.karazin.shop.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.karazin.shop.service.ProductCart;
import edu.karazin.shop.service.ProductService;

@RestController
@RequestMapping("order")
public class OrderRestController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderRestController.class);

	private final ProductCart productCart;
	private final ProductService productService;
	
	public OrderRestController(@Autowired ProductCart productCart, @Autowired ProductService productService) {
		this.productCart = productCart;
		this.productService = productService;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "add")
	public String addProduct(@RequestBody Map<String, String> params, Model model) {
		productCart.addProduct(productService.getProduct(Long.parseLong(params.get("prodId"), 10)));
		log.info("Product added");
		return "success";
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "delete-order-item")
	public String deleteOrderItem(@RequestBody Map<String, String> params) {
		productCart.removeOrderItem(Long.parseLong(params.get("id"), 10));
		log.info("=====================");
		log.info("Product from cart deleted");
		return "success";
	}


}
