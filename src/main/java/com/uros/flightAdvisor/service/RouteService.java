package com.uros.flightAdvisor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.uros.flightAdvisor.domain.administration.Airport;
import com.uros.flightAdvisor.domain.administration.Route;
import com.uros.flightAdvisor.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private AirportService airportService;

	public Route save(Route route) {
		return routeRepository.save(route);
	}

	public void readRoutesFromFile(String path) {
		try {
			File file = ResourceUtils.getFile(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.equals("")) {
					continue;
				}
				String tokens[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				String sourceAirportId = tokens[3];
				String destinationAirportId = tokens[5];
				if (sourceAirportId.isEmpty() || sourceAirportId.equals("\\N") || destinationAirportId.isEmpty()
						|| destinationAirportId.equals("\\N")) {
					continue;
				}

				Airport sourceAirport = airportService.findByAirportId(Long.parseLong(sourceAirportId));
				Airport destinationAirport = airportService.findByAirportId(Long.parseLong(destinationAirportId));
				if (sourceAirport != null && destinationAirport != null) {
					save(new Route(tokens[0], tokens[1], sourceAirport, destinationAirport,
							tokens[6].isEmpty() ? false : true, Integer.parseInt(tokens[7]), tokens[8],
							Double.parseDouble(tokens[9])));
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
