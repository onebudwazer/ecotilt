package fr.geodesy;

public class Main {

	public static void main(String[] args) {
		GeodeticCalculator geoCalc = new GeodeticCalculator();

		Ellipsoid reference = Ellipsoid.WGS84;
		GlobalPosition pointA = new GlobalPosition(43.087887, 5.878291, 50.0); // Point
																				// A
		GlobalPosition pointB = new GlobalPosition(43.354886, 5.448699, 155.0); // Point
																				// B
		double distance = geoCalc.calculateGeodeticCurve(reference, pointA,
				pointB).getEllipsoidalDistance(); // Distance between Point A
													// and Point B
		System.out.println(distance + " meters");
	}
}
