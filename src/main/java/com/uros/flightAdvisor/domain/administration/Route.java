package com.uros.flightAdvisor.domain.administration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Airport entity.
 * 
 * @author Uros
 *
 */
@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 2, max = 3)
	private String airlineCode;

	private String airlineId;

	private boolean codeshare;

	private int stops;

	private String equipment;

	private double price;

	@ManyToOne
	private Airport sourceAirport;

	@ManyToOne
	private Airport destinationAirport;

	@NotNull
	private boolean deleted = false;

	public Route(@NotNull @Size(min = 2, max = 2) String airlineCode, String airlineId, Airport sourceAirport,
			Airport destinationAirport, boolean codeshare, int stops, String equipment, double price) {
		this.airlineCode = airlineCode;
		this.airlineId = airlineId;
		this.codeshare = codeshare;
		this.stops = stops;
		this.equipment = equipment;
		this.price = price;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
	}

	public Route() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public boolean isCodeshare() {
		return codeshare;
	}

	public void setCodeshare(boolean codeshare) {
		this.codeshare = codeshare;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", airlineCode=" + airlineCode + ", airlineId=" + airlineId + ", codeshare="
				+ codeshare + ", stops=" + stops + ", equipment=" + equipment + ", price=" + price + ", sourceAirport="
				+ sourceAirport + ", destinationAirport=" + destinationAirport + ", deleted=" + deleted + "]";
	}
}
