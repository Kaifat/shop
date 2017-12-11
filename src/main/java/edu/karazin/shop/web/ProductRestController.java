package edu.karazin.shop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.ProductService;

@RestController
@RequestMapping("product") 
public class ProductRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	private final ProductService productService;

	public ProductRestController(@Autowired ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public String removeProduct(Model model, Product product) {
		log.info("Remove product");
		productService.removeProduct(product.getId());
		return "success";
	}

}
