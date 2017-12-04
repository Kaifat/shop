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

	
	private static final Logger log = LoggerFactory.getLogger(ProductCartImpl.class);
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final List<Product> products = new ArrayList<>();
	
	@Autowired
    public ProductCartImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, 
    		UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void addProduct(Product prod) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (auth.getName() == "anonymousUser") {
	    	products.add(prod);
	    } else {
	    	User user = this.userRepository.findByLogin(auth.getName());
	    	Cart cart = new Cart(user, "new", new Date());
	    	this.cartRepository.save(cart);
	    	CartItem cartItem = new CartItem(cart, prod, 1, prod.getCost());
	    	this.cartItemRepository.save(cartItem);
	    }
	}

	@Override
	public void removeProduct(Long prodId) {
		products.removeIf(item -> item.getId() == prodId);
		
	}

}
