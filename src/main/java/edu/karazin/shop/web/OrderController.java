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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String cart(Model model) {
		model.addAttribute("cartItems", orderService.getCartItems());
		int totalCount = orderService.getCartItems().stream().mapToInt(item -> item.getAmount()).sum();
		model.addAttribute("totalCount", totalCount);
		double totalSum = orderService.getCartItems().stream().map(item -> {
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
	public String orderCheckout(Model model, Order order, RedirectAttributes redirectAttributes) {

		if (!orderService.allItemsAreAvaliable()) {
			return "redirect:/order";
		}

		Long orderResultId = orderService.checkout(order.getAddress(), order.getEmail(), order.getPhone());		

		redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE",
				"Order #" + orderResultId  +  " created successfully. Thank you!");
		
		return "redirect:/products";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "list")
	public String getOrderList(Model model) {
		log.info("Show Order List");
		model.addAttribute("orderList", orderService.getOrders());
		return "order-list";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "details/{id}")
	public String getOrderDetails(Model model, Order order) {
		log.info("Show Order Details");
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		
		List <OrderItem> orderItems = orderService.getOrderItems(order.getId());
		model.addAttribute("orderItems", orderItems);
		model.addAttribute("orderId", order.getId());
		
		int totalCount = orderItems.stream().mapToInt(item -> item.getAmount()).sum();
		model.addAttribute("totalCount", totalCount);
		double totalSum = orderItems.stream().map(item -> {
			return item.getPrice() * item.getAmount();
		}).reduce(0.0, (x, y) -> x + y);
		model.addAttribute("totalSum", totalSum);
		return "order-details";
	}

}
