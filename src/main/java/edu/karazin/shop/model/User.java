package edu.karazin.shop.model;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
    @GeneratedValue
	private Long id;
	
	private String lastName;
	
	private String firstName;
	
	private String login;
	
	private String password;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public User() {
	}
	
	public User(String login, String password, Role role) {
		this(login, password, role, null, null, null);
	}
	
	public User(String login, String password, Role role, String lastName, String firstName, String email) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
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
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
