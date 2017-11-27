package edu.karazin.shop.dao;

import edu.karazin.shop.model.User;

public interface UserDao {
	
	User findById(Long id);	
	
	User save(User user);	
}
