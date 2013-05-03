package fr.ecotilt.appui.model;

public class Velib extends AGenericObject {

	public Velib() {
	}

	public Velib(String name, String city, int cp, GeoCoord gc, PictureEntity pe) {
		this.name = name;
		this.gc = gc;
		this.codePostal = cp;
		this.city = city;
		this.pe = pe;
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(this.name);
		b.append(" ");
		b.append(this.codePostal);
		b.append(" ");
		b.append(this.city);
		return b.toString();
	}
}
