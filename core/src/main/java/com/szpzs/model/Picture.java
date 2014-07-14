package com.szpzs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PICTURES database table.
 * 
 */
@Entity
@Table(name="PICTURES")
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final Long serialVersionUID = 1L;

	@SequenceGenerator(name="Picture", sequenceName="PICTURES_SEQ")
	@Id @GeneratedValue(generator="Picture")
	private Long id;

	@Column(name="\"LINK\"")
	private String link;
	
	@Column(name="UPLOAD_TIME")
	private Date uploadTime;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="pictures", cascade = CascadeType.ALL)
	private List<Product> products;
	
	public Picture() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Date getUploadTime() {
		return this.uploadTime;
	}
	
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}