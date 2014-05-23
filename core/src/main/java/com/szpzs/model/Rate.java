package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RATES database table.
 * 
 */
@Entity
@Table(name="RATES")
@NamedQuery(name="Rate.findAll", query="SELECT r FROM Rate r")
public class Rate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private BigDecimal positive;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="RATED_ID")
	private BigDecimal ratedId;

	@Column(name="RATER_ID")
	private BigDecimal raterId;

	private String text;

	public Rate() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getPositive() {
		return this.positive;
	}

	public void setPositive(BigDecimal positive) {
		this.positive = positive;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getRatedId() {
		return this.ratedId;
	}

	public void setRatedId(BigDecimal ratedId) {
		this.ratedId = ratedId;
	}

	public BigDecimal getRaterId() {
		return this.raterId;
	}

	public void setRaterId(BigDecimal raterId) {
		this.raterId = raterId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}