package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;

public interface ProductCart {
	
	List<OrderItem> getOrderItems();

	void addProduct(Product prod);
	
	void buyProducts();

	void removeOrderItem(Long orderItemId);

}
