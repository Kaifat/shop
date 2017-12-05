package edu.karazin.shop.dao;

import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Cart;
import edu.karazin.shop.model.User;

public interface CartRepository extends CrudRepository<Cart, Long> {
	
	Cart findFirstByUserAndStatus(User user, String status);
	
}
