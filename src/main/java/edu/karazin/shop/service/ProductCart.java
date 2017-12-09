package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.CartItem;
import edu.karazin.shop.model.Product;

public interface ProductCart {
	
	List<CartItem> getCartItems();

	void addProduct(Product prod);
	
	void buyProducts();

	void removeCartItem(Long cartItemId);

}
