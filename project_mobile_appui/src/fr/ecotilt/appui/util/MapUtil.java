package fr.ecotilt.appui.util;

import com.grum.geocalc.BoundingArea;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

import fr.ecotilt.appui.model.GeoCoord;

/**
 * Class qui regroupe les outils pour du calcul carto
 * 
 * @author Philippe
 * 
 */
public class MapUtil {

	private static final double DISTANCE_AREA = 100000; 

	public static double distFrom(GeoCoord geo1, GeoCoord geo2) {

		Coordinate lat1 = new DegreeCoordinate(geo1.getLatitude());
		Coordinate lng1 = new DegreeCoordinate(geo1.getLongitude());
		Point point1 = new Point(lat1, lng1);

		Coordinate lat2 = new DegreeCoordinate(geo2.getLatitude());
		Coordinate lng2 = new DegreeCoordinate(geo2.getLongitude());
		Point point2 = new Point(lat2, lng2);

		double distance = EarthCalc.getDistance(point1, point2);
		return distance;
	}

	public static GeoCoord distFrom(GeoCoord geo, double bearing,
			double distance) {

		Coordinate lat = new DegreeCoordinate(geo.getLatitude());
		Coordinate lng = new DegreeCoordinate(geo.getLongitude());
		Point point = new Point(lat, lng);
		Point otherPoint = EarthCalc.pointRadialDistance(point, 45, distance);

		return new GeoCoord(otherPoint.getLatitude(), otherPoint.getLongitude());
	}

	public static double getBearing(GeoCoord geo1, GeoCoord geo2) {
		Coordinate lat1 = new DegreeCoordinate(geo1.getLatitude());
		Coordinate lng1 = new DegreeCoordinate(geo1.getLongitude());
		Point point1 = new Point(lat1, lng1);

		Coordinate lat2 = new DegreeCoordinate(geo2.getLatitude());
		Coordinate lng2 = new DegreeCoordinate(geo2.getLongitude());
		Point point2 = new Point(lat2, lng2);

		double roulement = EarthCalc.getBearing(point1, point2);

		return roulement;
	}

//	public static BoundingArea getBoundingArea(GeoCoord geo) {
//		Coordinate lat = new DegreeCoordinate(geo.getLatitude());
//		Coordinate lng = new DegreeCoordinate(geo.getLongitude());
//		Point point = new Point(lat, lng);
//
//		BoundingArea area = EarthCalc.getBoundingArea(point, DISTANCE_AREA);
//		return area;
//	}
//
//	public static boolean getPointIsArea(BoundingArea area,
//			GeoCoord pointer) {
//
//		Coordinate latPointer = new DegreeCoordinate(pointer.getLatitude());
//		Coordinate lngPointer = new DegreeCoordinate(pointer.getLongitude());
//		Point pointFinder = new Point(latPointer, lngPointer);
//		
//		return area.isContainedWithin(pointFinder);
//	}
	
	public static boolean getCalculetLocation(GeoCoord myLocation, GeoCoord pointRef) {
		//ma position geo
		Coordinate lat = new DegreeCoordinate(myLocation.getLatitude());
		Coordinate lng = new DegreeCoordinate(myLocation.getLongitude());
		Point point = new Point(lat, lng);
		//rectangle bounding area
		BoundingArea area = EarthCalc.getBoundingArea(point, DISTANCE_AREA);
		
		//pointer pour la comparaison 
		Coordinate latPointer = new DegreeCoordinate(pointRef.getLatitude());
		Coordinate lngPointer = new DegreeCoordinate(pointRef.getLongitude());
		Point pointFinder = new Point(latPointer, lngPointer);
		
		boolean value =  area.isContainedWithin(pointFinder);
		return value;
	}

	public static void main(String[] args) {
		GeoCoord myLoc = new GeoCoord(42, -5005);
		GeoCoord obj2 = new GeoCoord(42, 18);
		
		boolean value = getCalculetLocation(myLoc, obj2);
		System.out.println(value);
		
		
//		System.out.println(obj1);
//		System.out.println(obj2);
//		double distance = distFrom(obj1, obj2);
//		System.out.println(distance + " meters");
//		double bearing = getBearing(obj1, obj2);
//		System.out.println(bearing);
//
//		GeoCoord newPoint = distFrom(obj2, bearing - 180, DISTANCE_AREA);
//		System.out.println(newPoint);
//
//		getBoundingArea(obj1);
//		GeoCoordArea zone = new GeoCoordArea(obj1, obj2);
//		
//		getPointIsArea(zone, new GeoCoord(50, 50));
		
		// System.out.println(kew);
		// Point otherPoint = EarthCalc.pointRadialDistance(kew, 0, 10);
		// System.out.println(otherPoint);
	}
	
	
	
	
	
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
	//
	// public static float distFrom(GeoCoord geo1, GeoCoord geo2) {
	// return distFrom(geo1.getLatitude(), geo1.getLongitude(),
	// geo2.getLatitude(), geo2.getLongitude());
	// }
}
