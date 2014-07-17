package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final Long serialVersionUID = 1L;
	
	@SequenceGenerator(name="Prod", sequenceName="PROD_SEQ")
	@Id @GeneratedValue(generator="Prod")
	private Long id;

	private BigInteger area;

	@Column(name="CATEGORY_ID")
	private BigInteger categoryId;

	@Column(name="CITY_ID")
	private BigInteger cityId;

	private String descriptions;

	private String name;

	@Column(name="OWNER_ID")
	private BigInteger ownerId;

	private BigInteger status;
	
	@Column(name="UPLOAD_TIME")
	private Date uploadTime;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Picture.class, cascade = CascadeType.ALL)
	@JoinTable(
	      name="PRODUCT_PICTURE",
	      joinColumns={@JoinColumn(name="product_id", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="picture_id", referencedColumnName="ID")})
	private List<Picture> pictures;
	
	public Product() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getArea() {
		return this.area;
	}

	public void setArea(BigInteger area) {
		this.area = area;
	}

	public BigInteger getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(BigInteger categoryId) {
		this.categoryId = categoryId;
	}

	public BigInteger getCityId() {
		return this.cityId;
	}

	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigInteger ownerId) {
		this.ownerId = ownerId;
	}

	public BigInteger getStatus() {
		return this.status;
	}

	public void setStatus(BigInteger status) {
		this.status = status;
	}
	
	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	public List<Picture> getPictures() {
		return this.pictures;
	}
}