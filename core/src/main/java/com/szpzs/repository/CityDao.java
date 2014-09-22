package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.City;
import com.szpzs.model.County;

public interface CityDao {

	public List<City> ListAllCity();
	
	public List<County> getCounties();
	
	public String SaveCity(City city);
}
