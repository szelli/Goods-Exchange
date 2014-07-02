package com.szpzs.model;

import java.math.BigInteger;

public class ProductListDatas {
	
	private BigInteger id;
	
	private String sort;
	
	private int limit;
	
	private int page;
	
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
	
	public int getPage(){
		return this.page;
	}
	
	public void setPage(int page){
		this.page = page;
	}
}
