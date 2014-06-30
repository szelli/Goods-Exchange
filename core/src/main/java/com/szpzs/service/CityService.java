package com.szpzs.service;

import java.util.List;

import com.szpzs.model.City;

public interface CityService {

	public List<City> ListAllCity();
	public String SaveCity(City city);
}
