package edu.karazin.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {
	
	@Id
    @GeneratedValue
	private Long id;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String email;
	
	public User() {
	}
	
	public User (Long id, String lastName, String firstName, String password, String email) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPassword() {
		return lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
