package fr.ecotilt.appui.model;

import java.io.Serializable;

public class Velib extends AGenericObject implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 4734731725518709104L;

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
