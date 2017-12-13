package edu.karazin.shop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.karazin.shop.dao.ProductRepository;
import edu.karazin.shop.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@Override
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public List<Product> searchProducts(String searchText, boolean deleted) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return (List<Product>) productRepository.findAllByDeleted(deleted);
		}
		
		return productRepository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchText, searchText);
	}

	@Override
	@Transactional
	public Long addProduct(Product prod) {
		return productRepository.save(prod).getId();
	}

	@Override
	@Transactional
	public void updateProduct(Product prod) {
		productRepository.save(prod);
	}

	@Override
	@Transactional
	public void removeProduct(Long id) {
		productRepository.delete(id);
	}
	
	@Override
	@Transactional
	public void restoreProduct(Long id) {
		Product product = productRepository.findOne(id);
		product.setDeleted(false);
		productRepository.save(product);
	}
}
