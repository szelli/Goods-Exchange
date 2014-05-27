package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the RATES database table.
 * 
 */
@Entity
@Table(name="RATES")
@NamedQuery(name="Rate.findAll", query="SELECT r FROM Rate r")
public class Rate implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;

	private BigInteger positive;

	@Column(name="PRODUCT_ID")
	private BigInteger productId;

	@Column(name="RATED_ID")
	private BigInteger ratedId;

	@Column(name="RATER_ID")
	private BigInteger raterId;

	private String text;

	public Rate() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getPositive() {
		return this.positive;
	}

	public void setPositive(BigInteger positive) {
		this.positive = positive;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getRatedId() {
		return this.ratedId;
	}

	public void setRatedId(BigInteger ratedId) {
		this.ratedId = ratedId;
	}

	public BigInteger getRaterId() {
		return this.raterId;
	}

	public void setRaterId(BigInteger raterId) {
		this.raterId = raterId;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}