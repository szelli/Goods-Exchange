package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the COUNTIES database table.
 * 
 */
@Entity
@Table(name="COUNTIES")
@NamedQuery(name="County.findAll", query="SELECT c FROM County c")
public class County implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name="COUNTY")
	private String county;

	public County() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
}
