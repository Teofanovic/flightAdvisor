package com.uros.flightAdvisor.domain.administration;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Airport entity.
 * 
 * @author Uros
 *
 */
@Entity
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private long airportId;

	@NotNull
	@Size(min = 2, max = 100)
	private String name;

	@Size(max = 3)
	@Column(name = "iata")
	private String iata;

	@Size(max = 4)
	@Column(name = "icao")
	private String icao;

	private double latitude;

	private double longitude;

	private double altitude;

	// Hours offset from UTC
	private double utcHoursOffset;

	private DaylightSavingsTime daylightSavingsTime;

	// Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
	private String timezone;

	private String type;

	@ManyToOne
	private City city;

	@OneToMany(mappedBy = "sourceAirport")
	private Set<Route> sourceRoutes = new HashSet<>();

	@OneToMany(mappedBy = "destinationAirport")
	private Set<Route> destinationRoutes = new HashSet<>();

	@NotNull
	private boolean deleted = false;

	public Airport(long airportId, @NotNull @Size(min = 2, max = 100) String name, @Size(min = 1, max = 3) String iata,
			@Size(min = 1, max = 4) String icao, double latitude, double longitude, double altitude, double utcHoursOffset,
			String daylightSavingsTime, String timezone, String type, City city) {
		this.airportId = airportId;
		this.name = name;
		this.iata = iata;
		this.icao = icao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.utcHoursOffset = utcHoursOffset;
		this.daylightSavingsTime = DaylightSavingsTime.fromString(daylightSavingsTime);
		this.timezone = timezone;
		this.type = type;
		this.city = city;
	}

	public Airport() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getAirportId() {
		return airportId;
	}

	public void setAirportId(long airportId) {
		this.airportId = airportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getUtcHoursOffset() {
		return utcHoursOffset;
	}

	public void setUtcHoursOffset(double utcHoursOffset) {
		this.utcHoursOffset = utcHoursOffset;
	}

	public DaylightSavingsTime getDaylightSavingsTime() {
		return daylightSavingsTime;
	}

	public void setDaylightSavingsTime(DaylightSavingsTime daylightSavingsTime) {
		this.daylightSavingsTime = daylightSavingsTime;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Set<Route> getSourceRoutes() {
		return sourceRoutes;
	}

	public void setSourceRoutes(Set<Route> sourceRoutes) {
		this.sourceRoutes = sourceRoutes;
	}

	public Set<Route> getDestinationRoutes() {
		return destinationRoutes;
	}

	public void setDestinationRoutes(Set<Route> destinationRoutes) {
		this.destinationRoutes = destinationRoutes;
	}

	@Override
	public String toString() {
		return "Airport [id=" + id + ", airportId=" + airportId + ", name=" + name + ", IATA=" + iata + ", ICAO=" + icao
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + ", utcHoursOffset="
				+ utcHoursOffset + ", daylightSavingsTime=" + daylightSavingsTime + ", timezone=" + timezone + ", type="
				+ type + ", city=" + city + ", deleted=" + deleted + "]";
	}
}
