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

import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.OrderService;
import edu.karazin.shop.service.ProductService;

@RestController
@RequestMapping("order")
public class OrderRestController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderRestController.class);

	private final OrderService orderService;
	private final ProductService productService;
	
	public OrderRestController(@Autowired OrderService orderService, @Autowired ProductService productService) {
		this.orderService = orderService;
		this.productService = productService;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "add")
	public String addProduct(@RequestBody Map<String, String> params) {
		orderService.addProduct(productService.getProduct(Long.parseLong(params.get("prodId"), 10)));
		log.info("Product added");
		return "success";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "change-amount")
	public String changeAmount(@RequestBody Map<String, String> params) {
		
		Long id = Long.parseLong(params.get("prodId"), 10);
		int amount = Integer.parseInt(params.get("amount"), 10);
		
		orderService.changeAmount(id, amount);
		return "success";
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "delete-order-item")
	public String deleteOrderItem(@RequestBody Map<String, String> params) {
		orderService.removeOrderItem(Long.parseLong(params.get("id"), 10));
		log.info("=====================");
		log.info("Product from cart deleted");
		return "success";
	}

}
