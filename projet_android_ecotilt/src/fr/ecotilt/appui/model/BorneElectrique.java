package fr.ecotilt.appui.model;

public class BorneElectrique extends AGenericObject {
	
	public BorneElectrique() {
	}

	public BorneElectrique(String name, String city, int cp, GeoCoord gc, PictureEntity pe) {
		this.name = name;
		this.gc = gc;
		this.codePostal = cp;
		this.city = city;
		this.pe = pe;
	}
}
