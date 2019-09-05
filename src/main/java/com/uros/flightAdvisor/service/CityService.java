package com.uros.flightAdvisor.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uros.flightAdvisor.domain.administration.City;
import com.uros.flightAdvisor.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public City findOne(Long id) {
		City city = cityRepository.findById(id).orElse(null);
		if (city == null) {
			throw new EntityNotFoundException("City with id " + id + " not found");
		}
		return city;
	}

	public City findOne(Long id, Integer limit) {
		City city = cityRepository.findById(id).orElse(null);
		if (city == null) {
			throw new EntityNotFoundException("City with id " + id + " not found");
		}
		if (limit != null) {
			// sort by creation date descending
			city.getComments().sort((c1, c2) -> c2.getCreationTime().compareTo(c1.getCreationTime()));
			// get top x comments
			city.setComments(city.getComments().stream().limit(limit).collect(Collectors.toList()));
		}
		return city;
	}

	public List<City> findAllLimitComments(Integer commentLimit) {
		List<City> cities = cityRepository.findAll();
		if (commentLimit != null) {
			for (City city : cities) {
				// sort by creation date descending
				city.getComments().sort((c1, c2) -> c2.getCreationTime().compareTo(c1.getCreationTime()));
				// get top x comments
				city.setComments(city.getComments().stream().limit(commentLimit).collect(Collectors.toList()));
			}
		}
		return cities;
	}

	public List<City> findAllByNameLimitComments(String name, Integer commentLimit) {
		List<City> cities = cityRepository.findByNameContainingIgnoreCase(name);
		if (commentLimit != null) {
			for (City city : cities) {
				// sort by creation date descending
				city.getComments().sort((c1, c2) -> c2.getCreationTime().compareTo(c1.getCreationTime()));
				// get top x comments
				city.setComments(city.getComments().stream().limit(commentLimit).collect(Collectors.toList()));
			}
		}
		return cities;
	}

	public List<City> findAll(String name, Integer commentLimit) {
		// check if search param is null or empty
		if (StringUtils.isBlank(name)) {
			return findAllLimitComments(commentLimit);
		} else {
			return findAllByNameLimitComments(name, commentLimit);
		}
	}

	public City save(City city) {
		return cityRepository.save(city);
	}

	public City findByNameIgnoreCase(String name) {
		return cityRepository.findByNameIgnoreCase(name);
	}
}
