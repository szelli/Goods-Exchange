package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the FORRENT database table.
 * 
 */
@Entity
@Table(name="FORRENT")
@NamedQuery(name="ForRent.findAll", query="SELECT f FROM ForRent f")
public class ForRent implements Serializable {
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="ForRent", sequenceName="FORRENT_SEQ1")
	@Id @GeneratedValue(generator="ForRent")
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Column(name="PRODUCT_ID")
	private BigInteger productId;

	@Temporal(TemporalType.DATE)
	private Date toDate;

	public ForRent() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public BigInteger getProductId() {
		return this.productId;
	}

	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}