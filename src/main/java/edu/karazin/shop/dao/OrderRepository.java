package edu.karazin.shop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	Order findFirstByUserAndStatus(User user, String status);
	
	List<Order> findByUser(User user);
	
}
