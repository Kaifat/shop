package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import edu.karazin.shop.model.Product;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class ProductCartImpl implements ProductCart {

	
//	private static final Logger log = LoggerFactory.getLogger(ProductCartImpl.class);

	private final List<Product> products = new ArrayList<>();

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void addProduct(Product prod) {
		products.add(prod);
	}

	@Override
	public void removeProduct(Long prodId) {
		products.removeIf(item -> item.getId() == prodId);
		
	}

}
