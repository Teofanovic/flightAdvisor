package com.uros.flightAdvisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uros.flightAdvisor.domain.administration.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByNameContainingIgnoreCase(String name);
	
	City findByNameIgnoreCase(String name);
}
