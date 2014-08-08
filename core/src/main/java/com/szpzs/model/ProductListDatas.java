package com.szpzs.model;

import java.math.BigInteger;

public class ProductListDatas {
	
	private BigInteger id;
	
	private String sort;
	
	private int limit;
	
	private int currentPage;
	
	private int pageCount;
	
	private int productsCount;
	
	private int offset;
	
	private String tab;
	
	private BigInteger categoryId;
	
	private BigInteger ownerId;
	
	public BigInteger getId(){
		return this.id;
	}
	
	public void setId(BigInteger id){
		this.id = id;
	}
	
	public String getSort(){
		return this.sort;
	}
	
	public void setSort(String sort){
		this.sort = sort;
	}
	
	public int getLimit(){
		return this.limit;
	}
	
	public void setLimit(int limit){
		this.limit = limit;
	}
	
	public int getCurentPage(){
		return this.currentPage;
	}
	
	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}
	
	public int getPageCount(){
		return this.pageCount;
	}
	
	public void setPageCount(int pageCount){
		this.pageCount=pageCount;
	}
	
	public int getProductsCount(){
		return this.productsCount;
	}
	
	public void setProductsCount(int productsCount){
		this.productsCount = productsCount;
	}
	
	public int getOffset(){
		return this.offset;
	}
	
	public void setOffset(int offset){
		this.offset = offset;
	}
	
	public String getTab(){
		return this.tab;
	}
	
	public void setTab(String tab){
		this.tab = tab;
	}
	
	public BigInteger getCategoryId(){
		return this.categoryId;
	}
	
	public void setCategoryId(BigInteger id){
		this.categoryId = id;
	}
	
	public BigInteger getOwnerId(){
		return this.ownerId;
	}
	
	public void setOwnerId(BigInteger id){
		this.ownerId = id;
	}
	
}
