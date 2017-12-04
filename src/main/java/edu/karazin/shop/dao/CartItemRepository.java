package edu.karazin.shop.dao;

import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
			
}

