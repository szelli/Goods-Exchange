package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the CATEGORIES database table.
 * 
 */
@Entity
@Table(name="CATEGORIES")
@NamedQuery(name="com.szpzs.model.Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {

	private static final Long serialVersionUID = 1L;
	
	@SequenceGenerator(name="Cat", sequenceName="CATEGORIES_SEQ")
	@Id @GeneratedValue(generator="Cat")
	private Long id;
	
	@Column(name="NAME")
	private String name;

	@Column(name="PARENT_ID")
	private Long parentId;
	
	public Category() {
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

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}