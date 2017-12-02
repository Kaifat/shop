package edu.karazin.shop.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import edu.karazin.shop.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}
	
	@Override
	public User findByEmailPassword(String email, String password) {
		User example = new User();
		example.setEmail(email);
		example.setPassword(password);
		return em.find(User.class, example);
		
	}
	
	@Override
	public User save(User user) {
		if (!em.contains(user)) {
			return em.merge(user);
		} else {
			em.persist(user);
			return user;
		}
	}

}
