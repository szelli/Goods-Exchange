package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the RESERVED database table.
 * 
 */
@Entity
@NamedQuery(name="Reserved.findAll", query="SELECT r FROM Reserved r")
public class Reserved implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"BEGIN\"")
	private Date begin;

	@Temporal(TemporalType.DATE)
	@Column(name="\"END\"")
	private Date end;

	@Column(name="PRODUCT_ID")
	private BigInteger productId;

	@Column(name="RESERVER_ID")
	private BigInteger reserverId;

	public Reserved() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBegin() {
		return this.begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return this.end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public BigInteger getReserverId() {
		return this.reserverId;
	}

	public void setReserverId(BigInteger reserverId) {
		this.reserverId = reserverId;
	}

}