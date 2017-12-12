package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.Order;
import edu.karazin.shop.model.OrderItem;
import edu.karazin.shop.model.Product;

public interface OrderService {
	
	List<OrderItem> getOrderItems();

	void addProduct(Product prod);
	
	void changeAmount(Long id, int amount);
	
	void checkout(String address, String email, String phone);
	
	boolean allItemsAreAvaliable();
	
	List<Order> getOrders();

	void removeOrderItem(Long itemId);

}
