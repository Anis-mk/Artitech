package com.example.arti;

import java.util.Collection;

import javax.persistence.*;

import com.example.arti.product.Product;


@Entity
@Table(name="users")

public class User {

	@Id
	private String username;
	private String password;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public Collection<Product> getProduits() {
		return produits;
	}


	public void setProduits(Collection<Product> produits) {
		this.produits = produits;
	}


	@ManyToMany
	@JoinTable(name="Users_Roles")
	private Collection<Role> roles;
	

	@ManyToMany
	@JoinTable(name="Panier")
	private Collection<Product> produits;


	
}
