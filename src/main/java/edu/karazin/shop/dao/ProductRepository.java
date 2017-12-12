package edu.karazin.shop.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.karazin.shop.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining
		(String title, String description);
	
	List<Product> findAllByDeleted(boolean deleted);
		
}
