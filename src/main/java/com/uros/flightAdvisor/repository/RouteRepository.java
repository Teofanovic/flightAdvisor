package com.uros.flightAdvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uros.flightAdvisor.domain.administration.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
