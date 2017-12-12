package edu.karazin.shop.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.User;
import edu.karazin.shop.service.OrderService;
import edu.karazin.shop.service.ProductService;

@Controller
@RequestMapping("order")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private final OrderService orderService;
	private final UserRepository userRepository;

	public OrderController(@Autowired OrderService orderService, UserRepository userRepository) {
		this.orderService = orderService;
		this.userRepository = userRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("cartItems", orderService.getOrderItems());
		int totalCount = orderService.getOrderItems().stream().mapToInt(item -> item.getAmount()).sum();
		model.addAttribute("totalCount", totalCount);
		double totalSum = orderService.getOrderItems().stream().map(item -> {
			return item.getPrice() * item.getAmount();
		}).reduce(0.0, (x, y) -> x + y);
		model.addAttribute("totalSum", totalSum);
		return "cart";
	}

	@RequestMapping(method = RequestMethod.GET, path = "checkout")
	public String formOrderCheckout(Model model) {
		
		if (!orderService.allItemsAreAvaliable()) {
			return "redirect:/order";
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		if (user == null) {
			user = new User();
		}
		model.addAttribute("deliveryDetails", user);
		return "checkout";
	}

	@RequestMapping(method = RequestMethod.POST, path = "checkout")
	public String orderCheckout(Model model, Order order) {

		if (!orderService.allItemsAreAvaliable()) {
			return "redirect:/order";
		}

		orderService.checkout(order.getAddress(), order.getEmail(), order.getPhone());

		model.addAttribute("success", "Order created successfully. Thank you!");
		return "checkout-success";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "list")
	public String getOrderList(Model model) {
		log.info("Show Order List");
		model.addAttribute("orderList", orderService.getOrders());
		return "order-list";
	}

}
