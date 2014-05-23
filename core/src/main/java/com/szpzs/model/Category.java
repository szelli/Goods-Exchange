package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CATEGORYS database table.
 * 
 */
@Entity
@Table(name="CATEGORYS")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	@Column(name="PARENT_ID")
	private BigDecimal parentId;

	public Category() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

}