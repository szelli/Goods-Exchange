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
	
	@SequenceGenerator(name="User", sequenceName="USERS_SEQ1")
	@Id @GeneratedValue(generator="User")
	private Long id;
	
	private String address;
	
	@Column(name="CITY_ID")
	private Long city;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="FULL_NAME")
	private String fullName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="POSTCODE")
	private BigInteger postcode;

	@ManyToOne
	@JoinColumn(name="\"ROLE\"")
	private Role role;
	
	@Column(name="ENABLED")
	private BigInteger enabled;

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

	public Long getCityId() {
		return this.city;
	}

	public void setCityId(Long city) {
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

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public BigInteger getStatus() {
		return this.enabled;
	}

	public void setStatus(BigInteger status) {
		this.enabled = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}