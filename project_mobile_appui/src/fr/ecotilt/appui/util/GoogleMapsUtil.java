package fr.ecotilt.appui.util;

public class GoogleMapsUtil {

	public String getLinkGoogleMaps(double latitude, double longitude) {
		String lat = String.valueOf(latitude);
		String lng = String.valueOf(longitude);
		return "https://maps.google.com/maps?q=" + lat + "," + lng;
	}
	
}
