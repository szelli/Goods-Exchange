package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the CATEGORYS database table.
 * 
 */
@Entity
@Table(name="CATEGORIES")
@NamedQuery(name="Categories.findAll", query="SELECT c FROM Categories c")
public class Categories implements Serializable {
	private static final Long serialVersionUID = 1L;
	
	@SequenceGenerator(name="Cat", sequenceName="CATEGORIES_SEQ")
	@Id @GeneratedValue(generator="Cat")
	private Long id;

	private String name;

	@Column(name="PARENT_ID")
	private BigInteger parentId;

	public Categories() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getParentId() {
		return this.parentId;
	}

	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}

}