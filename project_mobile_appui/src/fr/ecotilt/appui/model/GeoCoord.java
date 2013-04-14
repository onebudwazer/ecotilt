package fr.ecotilt.appui.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "GEOCOORD")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
public class GeoCoord {

	@Id
	@GeneratedValue
	@Column(name = "GEOCOORD_ID")
	private long geoCoordId;
	
	@Column(name = "LATITUDE")
	private double latitude;
	
	@Column(name = "LONGITUDE")
	private double longitude;
	
	public GeoCoord() {
	}
	
	public GeoCoord(double latitude, double longitude) {
		this.latitude  = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public long getGeoCoordId() {
		return this.geoCoordId;
	}
	
	public void setGeoCoordId(long id) {
		this.geoCoordId = id;
	}
	
	@Override
	public String toString() {
		return "GeoCoord{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
	}
	
}
