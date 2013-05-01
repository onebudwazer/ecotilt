package fr.ecotilt.appui.model;

import java.io.Serializable;


public class GeoCoord implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -3242482947496773453L;

	private long geoCoordId;
	
	private double latitude;
	
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
