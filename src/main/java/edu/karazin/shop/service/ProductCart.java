package edu.karazin.shop.service;

import java.util.List;
import edu.karazin.shop.model.Product;

public interface ProductCart {
	
	List<Product> getProducts();

	void addProduct(Product prod);

	void removeProduct(Long prodId);

}
