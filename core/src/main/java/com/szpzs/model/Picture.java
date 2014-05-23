package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PICTURES database table.
 * 
 */
@Entity
@Table(name="PICTURES")
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="\"LINK\"")
	private String link;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	public Picture() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

}