package com.szpzs.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the CITIES database table.
 * 
 */
@Entity
@Table(name="PRODUCT_PICTURE")
@NamedQuery(name="ProductPicture.findAll", query="SELECT p FROM ProductPicture p")
public class ProductPicture implements Serializable {
	private static final Long serialVersionUID = 1L;
	
	@SequenceGenerator(name="ProdPic", sequenceName="PROD_PIC_SEQ")
	@Id @GeneratedValue(generator="ProdPic")
	private Long id;
	
	@Column(name="PRODUCT_ID")
	private BigInteger productId;
	
	@Column(name="PICTURE_ID")
	private BigInteger pictureId;
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public BigInteger getProductId(){
		return productId;
	}
	
	public void setProductId(BigInteger productId){
		this.productId = productId; 
	}
	
	public BigInteger getPictureId(){
		return pictureId;
	}
	
	public void setPictureId(BigInteger pictureId){
		this.pictureId = pictureId;
	}
}
