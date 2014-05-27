package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PICTURES database table.
 * 
 */
@Entity
@Table(name="PICTURES")
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(name="\"LINK\"")
	private String link;

	@Column(name="PRODUCT_ID")
	private BigInteger productId;

	public Picture() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

}