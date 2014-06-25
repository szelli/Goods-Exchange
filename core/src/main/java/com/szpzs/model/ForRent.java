package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the FOR_RENT database table.
 * 
 */
@Entity
@Table(name="FORRENT")
@NamedQuery(name="ForRent.findAll", query="SELECT f FROM ForRent f")
public class ForRent implements Serializable {
	private static final Long serialVersionUID = 1L;

	@SequenceGenerator(name="ForRent", sequenceName="FORRENT_SEQ1")
	@Id @GeneratedValue(generator="ForRent")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="fromDate")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@Column(name="toDate")
	private Date toDate;

	@Column(name="PRODUCT_ID")
	private BigInteger productId;

	public ForRent() {
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

}