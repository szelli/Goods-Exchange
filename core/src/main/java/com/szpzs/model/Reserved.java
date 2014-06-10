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

	@SequenceGenerator(name="Reserved", sequenceName="RESERVED_SEQ1")
	@Id @GeneratedValue(generator="Reserved")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="FROMDATE")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@Column(name="TODATE")
	private Date toDate;

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

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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