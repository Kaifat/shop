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

import edu.karazin.shop.dao.CartItemRepository;
import edu.karazin.shop.dao.CartRepository;
import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Cart;
import edu.karazin.shop.model.CartItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.User;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class ProductCartImpl implements ProductCart {

	
//	private static final Logger log = LoggerFactory.getLogger(ProductCartImpl.class);
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final List<CartItem> cartItems = new ArrayList<>();
	
	
	@Autowired
    public ProductCartImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, 
    		UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

	@Override
	public List<CartItem> getCartItems() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() == "anonymousUser") {
			return cartItems;
		} else {
			User user = this.userRepository.findByLogin(auth.getName());
			Cart cart = this.cartRepository.findFirstByUserAndStatus(user, "new");
			return this.cartItemRepository.findByCart(cart);
		}
	}

	@Override
	public void addProduct(Product prod) {
		CartItem cartItem = new CartItem(prod, 1, prod.getCost());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (auth.getName() == "anonymousUser") {
	    	Boolean found = false;
	    	for (CartItem item : cartItems) { 
	    		if(item.getProduct().getId().equals(prod.getId())) { 
			       item.setAmount(item.getAmount() + 1);
			       found = true;
			   }
	    	}
	    	if (!found) {
	    		cartItems.add(cartItem);
	    	}
	    } else {
	    	User user = this.userRepository.findByLogin(auth.getName());
	    	Cart cart = this.cartRepository.findFirstByUserAndStatus(user, "new");
	    	
	    	if (cart == null) {
	    		cart = new Cart(user, "new", new Date());
	    		this.cartRepository.save(cart);
	    	}
	    	
	    	CartItem existedCartItem = this.cartItemRepository.findFirstByCartAndProduct(cart, prod);
	    	if (existedCartItem == null) {
	    		cartItem.setCart(cart);
	    		this.cartItemRepository.save(cartItem);
	    	} else {
	    		existedCartItem.setAmount(existedCartItem.getAmount() + 1);
	    		this.cartItemRepository.save(existedCartItem);
	    	}
	    	
	    }
	}
	
	@Override
	public void buyProducts() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = this.userRepository.findByLogin(auth.getName());
		Cart cart = this.cartRepository.findFirstByUserAndStatus(user, "new");
		cart.setStatus("done");
		this.cartRepository.save(cart);
		
	}

	@Override
	public void removeCartItem(Long itemId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName() == "anonymousUser") {
			cartItems.removeIf(item -> item.getProduct().getId() == itemId);
		} else {
			this.cartItemRepository.delete(itemId);
		}
		
	}
}
