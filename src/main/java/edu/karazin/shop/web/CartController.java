package edu.karazin.shop.web;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.karazin.shop.service.ProductCart;
import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("cart")
public class CartController {
	
//	private static final Logger log = LoggerFactory.getLogger(CartController.class);

	private final ProductService productService;
	private final ProductCart productCart;

	public CartController(@Autowired ProductService productService, @Autowired ProductCart cartStore) {
		this.productService = productService;
		this.productCart = cartStore;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("cartItems", productCart.getCartItems());
		return "cart-list";
	}

	@RequestMapping(method = RequestMethod.GET, params = "add")
	public String addProduct(@RequestParam("prodId") Long prodId, Model model) {		
		productCart.addProduct(productService.getProduct(prodId));
		return list(model);
	}

	@RequestMapping(method = RequestMethod.GET, params = "delete")
	public String removeProduct(@RequestParam("itemId") Long itemId, Model model) {
		productCart.removeCartItem(itemId);
		return list(model);
	}

	
}
