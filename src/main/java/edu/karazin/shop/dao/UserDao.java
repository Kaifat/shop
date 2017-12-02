package edu.karazin.shop.dao;

import edu.karazin.shop.model.User;

public interface UserDao {
	
	User findById(Long id);
	
	User findByEmailPassword(String email, String password);
	
	User save(User user);	
}
