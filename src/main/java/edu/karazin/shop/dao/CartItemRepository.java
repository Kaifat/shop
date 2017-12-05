package edu.karazin.shop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Cart;
import edu.karazin.shop.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	
	List<CartItem> findByCart(Cart cart);
}

