package edu.karazin.shop.dao;

import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
			
}
