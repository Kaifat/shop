package edu.karazin.shop.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.karazin.shop.model.Product;
import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductEditController {

	private static final Logger log = LoggerFactory.getLogger(ProductEditController.class);

	private final ProductService productService;

	public ProductEditController(@Autowired ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newProduct(Model model) {
		log.info("Render form for new product");

		model.addAttribute("form_title", "Add new product");
		model.addAttribute("product", new Product("", ""));
		return "product-edit";
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public String editProduct(Model model, @PathVariable("id") Long productId) {
		log.info("Edit product");

		Product p = productService.getProduct(productId);
		if (p == null) {
			throw new NotFoundException();
		}

		model.addAttribute("form_title", "Edit product");
		model.addAttribute("product", p);
		return "product-edit";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addProduct(Model model, Product product,
		 @RequestParam(value = "file", required = false) MultipartFile file)
				 throws IOException {
		
		log.info("Add product");
		
		if (file != null && !file.isEmpty()) {
			product.setImage(file.getBytes());
			product.setImageMimeType(file.getContentType());
		}
		
		productService.addProduct(product);
		
		return "redirect:/products";
	}

	@RequestMapping(method = RequestMethod.POST, path = "{id}")
	public String updateProduct(Model model, Product product,
			@RequestParam(value = "file", required = false) MultipartFile file)
					throws IOException {
		
		log.info("Update product");
		
		if (file != null && !file.isEmpty()) {
			product.setImage(file.getBytes());
			product.setImageMimeType(file.getContentType());
		} else {
			Product oldProduct = productService.getProduct(product.getId());
			product.setImage(oldProduct.getImage());
			product.setImageMimeType(oldProduct.getImageMimeType());
		}
		
		productService.updateProduct(product);
		return "redirect:/products";
	}
	
}
