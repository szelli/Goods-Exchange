package com.szpzs.service;

import java.util.List;

import com.szpzs.model.City;
import com.szpzs.model.County;

public interface CityService {

	public List<City> ListAllCity();
	
	public List<County> getCounties();
	
	public String SaveCity(City city);
}
