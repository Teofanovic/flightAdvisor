package com.uros.flightAdvisor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.uros.flightAdvisor.domain.administration.Airport;
import com.uros.flightAdvisor.domain.administration.City;
import com.uros.flightAdvisor.repository.AirportRepository;

@Service
public class AirportService {

	@Autowired
	private AirportRepository airportRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	private RouteService routeService;

	public Airport save(Airport airport) {
		return airportRepository.save(airport);
	}

	public Airport findByAirportId(long id) {
		return airportRepository.findByAirportId(id);
	}

	// run method after Spring Boot starts
	@EventListener(ApplicationReadyEvent.class)
	public void readAllData() {
		readAirportsFromFile("classpath:data/airports.txt");
		routeService.readRoutesFromFile("classpath:data/routes.txt");
	}

	public void readAirportsFromFile(String path) {
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
				City city = cityService.findByNameIgnoreCase(stripDoubleQuotes(tokens[2]));
				// if (city != null) {
				save(new Airport(Long.parseLong(tokens[0]), stripDoubleQuotes(tokens[1]), stripDoubleQuotes(tokens[4]),
						stripDoubleQuotes(tokens[5]), tokens[6].equals("\\N") ? 0 : Double.parseDouble(tokens[6]),
						tokens[7].equals("\\N") ? 0 : Double.parseDouble(tokens[7]),
						tokens[8].equals("\\N") ? 0 : Double.parseDouble(tokens[8]),
						tokens[9].equals("\\N") ? 0 : Double.parseDouble(tokens[9]), stripDoubleQuotes(tokens[10]),
						stripDoubleQuotes(tokens[11]), stripDoubleQuotes(tokens[12]), city));
				// }
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Strips double quote characters from start and end of a string.
	 * 
	 * @param input
	 *            string
	 * @return stripped string
	 */
	private String stripDoubleQuotes(String input) {
		return StringUtils.strip(input, String.valueOf('"'));
	}

	/**
	 * Calculate distance between two points in latitude and longitude taking into
	 * account height difference. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters el2
	 * End altitude in meters
	 * 
	 * @returns Distance in Meters
	 */
	public static double calculateDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}
}
