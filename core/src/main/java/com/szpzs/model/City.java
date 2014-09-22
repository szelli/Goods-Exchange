package com.szpzs.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(name="CITIES")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final Long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="COUNTY_ID")
	private Long countyId;
	
	public City() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Long getCountyId() {
		return this.id;
	}

	public void setCountyId(Long id) {
		this.id = id;
	}
}