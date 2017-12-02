package edu.karazin.shop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	 @Autowired
     public UserServiceImpl(UserRepository userRepository) {
         this.userRepository = userRepository;
     }
	
	@Override
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	 @Override
     public User getUser(Long id) {
         return userRepository.findOne(id);
     }
 
     @Override
     public User getUser(String login) {
         return userRepository.findByLogin(login);
     }

}
