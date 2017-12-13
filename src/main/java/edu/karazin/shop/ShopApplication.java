package edu.karazin.shop;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import edu.karazin.shop.dao.ProductRepository;
import edu.karazin.shop.dao.UserRepository;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.Role;
import edu.karazin.shop.model.User;

@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;

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

				// Create default admin
				userRepository.save(new User("admin", "admin", Role.ROLE_ADMIN, "John", "Doe", "admin@admin.com",
						"Address", "1234567890"));

				// Create several users
				for (int i = 1; i <= 3; i++) {
					userRepository.save(new User("user" + i, "user" + i, Role.ROLE_USER, "Vasya" + i, "Pupkin" + i,
							"user" + i + "@user" + i + ".com", "Address", "1234567890"));
				}

				// Create several products
				File file = new File("D:\\pic.jpg");
		        byte[] bFile = new byte[(int) file.length()];
		        try {
		   	     FileInputStream fileInputStream = new FileInputStream(file);
		   	     //convert file into array of bytes
		   	     fileInputStream.read(bFile);
		   	     fileInputStream.close();
		           } catch (Exception e) {
		   	     e.printStackTrace();
		           }
				for (int i = 1; i <= 10; i++) {
					
					productRepository.save(
							new Product("Product_" + i, "Product_" + i + "_Description", bFile, "image/jpeg", 10.0 * i, 10 * i));
				}
			}
		};
	}
	
}
