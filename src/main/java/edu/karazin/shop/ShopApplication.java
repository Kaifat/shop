package edu.karazin.shop;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Role;
import edu.karazin.shop.model.User;

@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ShopApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	if (userRepository.findByLogin("admin") == null) { 
	    		userRepository.save(new User("admin", "admin", Role.ROLE_ADMIN));
	    	}
	      };
	   }
	
}
