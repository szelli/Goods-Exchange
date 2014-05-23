package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FOR_RENT database table.
 * 
 */
@Entity
@Table(name="FOR_RENT")
@NamedQuery(name="ForRent.findAll", query="SELECT f FROM ForRent f")
public class ForRent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="\"BEGIN\"")
	private Date begin;

	@Temporal(TemporalType.DATE)
	@Column(name="\"END\"")
	private Date end;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	public ForRent() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

}