package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;

	private String address;

	private String city;

	private String email;

	@Column(name="FULL_NAME")
	private String fullName;

	private String password;

	private BigInteger postcode;

	@Column(name="\"ROLE\"")
	private BigInteger role;

	private BigInteger status;

	@Column(name="USER_NAME")
	private String userName;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getPostcode() {
		return this.postcode;
	}

	public void setPostcode(BigInteger postcode) {
		this.postcode = postcode;
	}

	public BigInteger getRole() {
		return this.role;
	}

	public void setRole(BigInteger role) {
		this.role = role;
	}

	public BigInteger getStatus() {
		return this.status;
	}

	public void setStatus(BigInteger status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}