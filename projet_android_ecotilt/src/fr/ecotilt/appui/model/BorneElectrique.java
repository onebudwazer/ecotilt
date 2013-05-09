package fr.ecotilt.appui.model;

import java.io.Serializable;

public class BorneElectrique extends AGenericObject implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -3426546276898177605L;

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
