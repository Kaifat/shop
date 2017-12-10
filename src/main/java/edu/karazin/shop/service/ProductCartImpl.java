package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import edu.karazin.shop.dao.OrderItemRepository;
import edu.karazin.shop.dao.OrderRepository;
import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.User;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class ProductCartImpl implements ProductCart {

	
//	private static final Logger log = LoggerFactory.getLogger(ProductCartImpl.class);
	
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final UserRepository userRepository;
	private final List<OrderItem> orderItems = new ArrayList<>();
	
	
	@Autowired
    public ProductCartImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, 
    		UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

	@Override
	public List<OrderItem> getOrderItems() {
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
	
	@Override
	public void buyProducts() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		Order order = this.orderRepository.findFirstByUserAndStatus(user, "new");
		order.setStatus("done");
		this.orderRepository.save(order);
		
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
