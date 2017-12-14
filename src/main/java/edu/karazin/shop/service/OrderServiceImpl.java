package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import edu.karazin.shop.dao.OrderItemRepository;
import edu.karazin.shop.dao.OrderRepository;
import edu.karazin.shop.dao.ProductRepository;
import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.Role;
import edu.karazin.shop.model.User;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class OrderServiceImpl implements OrderService {

	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final List<OrderItem> orderItems = new ArrayList<>();
	
	@Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, 
    		UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

	@Override
	public List<OrderItem> getCartItems() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() == "anonymousUser") {
			return orderItems;
		} else {
			User user = this.userRepository.findByLogin(auth.getName());
			Order order = this.orderRepository.findFirstByUserAndStatus(user, "new");
			return this.orderItemRepository.findByOrder(order);
		}
	}
	
	@Override
	public List<OrderItem> getOrderItems(Long id) {
		Order order = this.orderRepository.findOne(id);
		return this.orderItemRepository.findByOrder(order);
	}

	@Override
	public void addProduct(Product prod) {
		OrderItem orderItem = new OrderItem(prod, 1, prod.getCost());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (auth.getName() == "anonymousUser") {
	    	Boolean found = false;
	    	for (OrderItem item : orderItems) { 
	    		if(item.getProduct().getId().equals(prod.getId())) { 
			       item.setAmount(item.getAmount() + 1);
			       found = true;
			   }
	    	}
	    	if (!found) {
	    		orderItems.add(orderItem);
	    	}
	    } else {
	    	User user = this.userRepository.findByLogin(auth.getName());
	    	Order order = this.orderRepository.findFirstByUserAndStatus(user, "new");
	    	
	    	if (order == null) {
	    		order = new Order(user, "new", new Date());
	    		this.orderRepository.save(order);
	    	}
	    	
	    	OrderItem existedCartItem = this.orderItemRepository.findFirstByOrderAndProduct(order, prod);
	    	if (existedCartItem == null) {
	    		orderItem.setOrder(order);
	    		this.orderItemRepository.save(orderItem);
	    	} else {
	    		existedCartItem.setAmount(existedCartItem.getAmount() + 1);
	    		this.orderItemRepository.save(existedCartItem);
	    	}
	    	
	    }
	}
	
	@Override
	public void changeAmount(Long id, int amount) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (auth.getName() == "anonymousUser") {
	    	for (OrderItem item : orderItems) { 
	    		if(item.getProduct().getId().equals(id)) { 
			       item.setAmount(amount);
			   }
	    	}
	    } else {
	    	OrderItem existedCartItem = this.orderItemRepository.findOne(id);
	    	existedCartItem.setAmount(amount);
    		this.orderItemRepository.save(existedCartItem);
	    }
	}
	
	// TODO: need refactoring
	@Override
	public Long checkout(String address, String email, String phone) {
		
		Order order = new Order();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userLogin = auth.getName();
		if (userLogin == "anonymousUser") {
			order.setStatus("new");
			order = this.orderRepository.save(order);
			for (OrderItem item : orderItems) {								
				item.setOrder(order);				
	    		this.orderItemRepository.save(item);
	    	}			
		} else {
			User user = this.userRepository.findByLogin(userLogin);
			order = this.orderRepository.findFirstByUserAndStatus(user, "new");
		}
			
		// decrease product balance for product according to each order item
		this.getCartItems().forEach(item -> {
			Product product = item.getProduct();
			product.setBalance(product.getBalance() - item.getAmount());
			this.productRepository.save(product);
		});		
		
		order.setAddress(address);
		order.setEmail(email);
		order.setPhone(phone);
		order.setStatus("done");
		order = this.orderRepository.save(order);
		
		return order.getId();
	
	}
	
	@Override
	public boolean allItemsAreAvaliable() {
		
		if (this.getCartItems().isEmpty()) {
			return false;
		}

		for (OrderItem item : this.getCartItems()) {
			if (item.getAmount() > item.getProduct().getBalance()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public List<Order> getOrders() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		Role role = user.getRole();
		if (role == Role.ROLE_ADMIN) {
			return (List<Order>) orderRepository.findAll();
		} else {
			return (List<Order>) orderRepository.findByUser(user);
		}
	}

	@Override
	public void removeOrderItem(Long itemId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() == "anonymousUser") {
			orderItems.removeIf(item -> item.getProduct().getId() == itemId);
		} else {
			this.orderItemRepository.delete(itemId);
		}
		
	}
}
