package fr.ecotilt.appui.util;

import fr.ecotilt.appui.model.GeoCoord;
import fr.geodesy.Ellipsoid;
import fr.geodesy.GeodeticCalculator;
import fr.geodesy.GlobalPosition;

/**
 * Classe qui regroupe les outils pour du calcul carto
 * 
 * @author Philippe
 */
public class MapManager {

	private double	DISTANCE_AREA	= 3000;

	private MapManager() {
	}

	private static class LazySingleton {
		private static MapManager	instance	= new MapManager();
	}

	public static MapManager getInstance() {
		return LazySingleton.instance;
	}

	public double setDistanceArea(double value) {
		return this.DISTANCE_AREA = value;
	}

	public double getDistanceArea() {
		return DISTANCE_AREA;
	}

	public boolean distFrom2points(GeoCoord myLocation, GeoCoord pointRef) {
		// ma position geo
		GlobalPosition pointA = new GlobalPosition(myLocation.getLatitude(),
				myLocation.getLongitude(), 0.0);

		GlobalPosition pointB = new GlobalPosition(pointRef.getLatitude(),
				pointRef.getLongitude(), 0.0);

		Ellipsoid reference = Ellipsoid.WGS84;
		GeodeticCalculator geoCalc = new GeodeticCalculator();

		// Distance between Point A and Point B
		double distance = geoCalc.calculateGeodeticCurve(reference, pointA,
				pointB).getEllipsoidalDistance();
		// System.out.println(distance + " meters");

		boolean value = false;
		if (distance <= DISTANCE_AREA) {
			value = true;
		}
		return value;
	}

	public static void main(String[] args) {
		GeoCoord myLoc = new GeoCoord(42.000001, 4.75000001);
		GeoCoord obj2 = new GeoCoord(42.000001, 4.750002);
		System.out.println(myLoc.toString());
		System.out.println(obj2.toString());

		boolean value = MapManager.getInstance().distFrom2points(myLoc, obj2);
		System.out.println(value);
	}

	// double distance = distFrom(obj1, obj2);
	// System.out.println(distance + " meters");
	// double bearing = getBearing(obj1, obj2);
	// System.out.println(bearing);
	// GeoCoord newPoint = distFrom(obj2, bearing - 180, DISTANCE_AREA);
	// System.out.println(newPoint);
	//
	// getBoundingArea(obj1);
	// GeoCoordArea zone = new GeoCoordArea(obj1, obj2);
	// getPointIsArea(zone, new GeoCoord(50, 50));

	// System.out.println(kew);
	// Point otherPoint = EarthCalc.pointRadialDistance(kew, 0, 10);
	// System.out.println(otherPoint);

	// @Deprecated
	// public static float distFrom(double lat1, double lng1, double lat2,
	// double lng2) {
	// double earthRadius = 3958.75;
	// double dLat = Math.toRadians(lat2 - lat1);
	// double dLng = Math.toRadians(lng2 - lng1);
	// double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	// + Math.cos(Math.toRadians(lat1))
	// * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
	// * Math.sin(dLng / 2);
	// double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	// double dist = earthRadius * c;
	//
	// int meterConversion = 1609;
	// return new Float(dist * meterConversion).floatValue();
	// }
	// public static BoundingArea getBoundingArea(GeoCoord geo) {
	// Coordinate lat = new DegreeCoordinate(geo.getLatitude());
	// Coordinate lng = new DegreeCoordinate(geo.getLongitude());
	// Point point = new Point(lat, lng);
	//
	// BoundingArea area = EarthCalc.getBoundingArea(point, DISTANCE_AREA);
	// return area;
	// }
	// public static boolean getPointIsArea(BoundingArea area,
	// GeoCoord pointer) {
	//
	// Coordinate latPointer = new DegreeCoordinate(pointer.getLatitude());
	// Coordinate lngPointer = new DegreeCoordinate(pointer.getLongitude());
	// Point pointFinder = new Point(latPointer, lngPointer);
	//
	// return area.isContainedWithin(pointFinder);
	// public static boolean getCalculetLocation(GeoCoord myLocation, GeoCoord
	// pointRef) {
	// //ma position geo
	// Coordinate lat = new DegreeCoordinate(myLocation.getLatitude());
	// Coordinate lng = new DegreeCoordinate(myLocation.getLongitude());
	// Point point = new Point(lat, lng);
	// //rectangle bounding area
	// BoundingArea area = EarthCalc.getBoundingArea(point, DISTANCE_AREA);
	// System.out.println(area.toString());
	// //pointer pour la comparaison
	// Coordinate latPointer = new DegreeCoordinate(pointRef.getLatitude());
	// Coordinate lngPointer = new DegreeCoordinate(pointRef.getLongitude());
	// Point pointFinder = new Point(latPointer, lngPointer);
	//
	// boolean value = area.isContainedWithin(pointFinder);
	// return value;
	// }
}
