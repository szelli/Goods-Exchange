package com.szpzs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.City;
import com.szpzs.model.County;
import com.szpzs.repository.CityDao;

@Service("CityServiceImpl")
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> ListAllCity() {
		return cityDao.ListAllCity();
	}
	
	@Override
	public List<County> getCounties() {
		return cityDao.getCounties();
	}
	
	@Override
	public String SaveCity(City city) {
		return cityDao.SaveCity(city);
	}

}
