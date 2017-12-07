package edu.karazin.shop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Cart;
import edu.karazin.shop.model.CartItem;
import edu.karazin.shop.model.Product;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	
	List<CartItem> findByCart(Cart cart);
	
	CartItem findFirstByCartAndProduct(Cart cart, Product product);
}

