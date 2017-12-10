package edu.karazin.shop.web;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.ProductCart;
import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("order")
public class OrderController {
	
//	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	private final ProductService productService;
	private final ProductCart productCart;
	private final UserRepository userRepository;

	public OrderController(@Autowired ProductService productService, @Autowired ProductCart cartStore,
			UserRepository userRepository) {
		this.productService = productService;
		this.productCart = cartStore;
		this.userRepository = userRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("cartItems", productCart.getOrderItems());
		int totalCount = productCart.getOrderItems().stream().mapToInt(item -> item.getAmount()).sum();
		model.addAttribute("totalCount", totalCount);
		double totalSum = productCart.getOrderItems().stream()
				.map(item -> {return item.getPrice()*item.getAmount();})
				.reduce(0.0, (x, y) -> x + y);
		model.addAttribute("totalSum", totalSum);
		return "cart";
	}

	@RequestMapping(method = RequestMethod.GET, params = "buy")
	public String buyProducts(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		if (user == null) {
			user = new User();
		}
		model.addAttribute("deliveryDetails", user);
		return "delivery";
	}
	
//	@RequestMapping(method = RequestMethod.POST, params = "delivery")
//	public String buyProducts() {
//		productCart.buyProducts();
//		return "delivery";
//	}
	
}
