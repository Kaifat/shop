package edu.karazin.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {

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
}
