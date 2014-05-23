package com.szpzs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(name="CITIES")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String city;

	public City() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}