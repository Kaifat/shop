package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;

public interface ProductCart {
	
	List<OrderItem> getOrderItems();

	void addProduct(Product prod);
	
	void changeAmount(Long id, int amount);
	
	void buyProducts();

	void removeOrderItem(Long itemId);

}
