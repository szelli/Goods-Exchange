package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal area;

	@Column(name="CATEGORY_ID")
	private BigDecimal categoryId;

	@Column(name="CITY_ID")
	private BigDecimal cityId;

	private String descriptions;

	private String name;

	@Column(name="OWNER_ID")
	private BigDecimal ownerId;

	private BigDecimal status;

	public Product() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getArea() {
		return this.area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(BigDecimal categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getCityId() {
		return this.cityId;
	}

	public void setCityId(BigDecimal cityId) {
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

	public BigDecimal getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(BigDecimal ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}