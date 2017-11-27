package edu.karazin.shop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.karazin.shop.dao.UserDao;
import edu.karazin.shop.model.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao dao;
	
	public UserServiceImpl(@Autowired UserDao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void saveUser(User user) {
		dao.save(user);
	}

}
