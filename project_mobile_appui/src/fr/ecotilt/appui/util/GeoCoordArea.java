package fr.ecotilt.appui.util;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.Point;

import fr.ecotilt.appui.model.GeoCoord;

public class GeoCoordArea {
	private GeoCoord northWest;
	private GeoCoord southEast;
	
	public GeoCoordArea(GeoCoord northWest, GeoCoord southEast) {
		this.northWest = northWest;
		this.southEast = southEast;
	}
	
	public Point getPointSouthEast() {
		Coordinate lat = new DegreeCoordinate(southEast.getLatitude());
		Coordinate lng = new DegreeCoordinate(southEast.getLongitude());
		return new Point(lat, lng);
	}
	
	public Point getPointNorthWest() {
		Coordinate lat = new DegreeCoordinate(northWest.getLatitude());
		Coordinate lng = new DegreeCoordinate(northWest.getLongitude());
		return new Point(lat, lng);
	}
}
