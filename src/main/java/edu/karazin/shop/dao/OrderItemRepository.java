package edu.karazin.shop.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	
	List<OrderItem> findByOrder(Order order);
	
	OrderItem findFirstByOrderAndProduct(Order order, Product product);
}

