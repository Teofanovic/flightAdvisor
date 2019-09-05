package com.uros.flightAdvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uros.flightAdvisor.domain.administration.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	Airport findByAirportId(long id);
}
