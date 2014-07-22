package com.szpzs.repository;

import java.util.List;

import com.szpzs.model.City;

public interface CityDao {

	public List<City> ListAllCity();
	public String SaveCity(City city);
}
