package com.szpzs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.City;

@Repository
public class CityDaoImpl implements CityDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<City> ListAllCity() {
		List<City> cities = entityManager.createQuery("SELECT c FROM City c").getResultList();
		return cities;
	}

	@Override
	@Transactional
	public String SaveCity(City city) {
		entityManager.persist(city);
		entityManager.flush();
		return "OK";
	}

}
