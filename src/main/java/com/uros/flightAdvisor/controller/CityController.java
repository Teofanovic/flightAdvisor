package com.uros.flightAdvisor.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uros.flightAdvisor.domain.administration.City;
import com.uros.flightAdvisor.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<City>> getCities(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "comment-limit", required = false) Integer commentLimit) {
		return ResponseEntity.ok(cityService.findAll(name, commentLimit));
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<City> getCity(@PathVariable("id") Long id,
			@RequestParam(name = "comment-limit", required = false) Integer commentLimit) {
		try {
			return ResponseEntity.ok(cityService.findOne(id, commentLimit));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<City> postCity(@RequestBody City city) {
		city = cityService.save(city);
		try {
			return ResponseEntity.created(new URI(String.format("/cities/%s", city.getId()))).body(city);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
