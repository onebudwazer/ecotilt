package fr.ecotilt.appui.model;

import java.io.Serializable;

public class Pompe extends AGenericObject implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -4683827843050356958L;

	public Pompe() {
	}

	public Pompe(String name, String city, int cp, GeoCoord gc, PictureEntity pe) {
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
