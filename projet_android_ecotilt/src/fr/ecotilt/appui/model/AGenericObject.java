package fr.ecotilt.appui.model;

import java.io.Serializable;
import java.util.Date;

public abstract class AGenericObject implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 2632816506703165226L;

	private int				id;

	protected String		name;

	protected int			codePostal;

	protected GeoCoord		gc;

	protected String		city;

	protected PictureEntity	pe;

	protected Category		category;

	protected Date			date;

	public AGenericObject() {
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoCoord getGeoCoord() {
		return gc;
	}

	public void setGeoCoord(GeoCoord gc) {
		this.gc = gc;
	}

	public PictureEntity getPictureEntity() {
		return pe;
	}

	public void setPictureEntity(PictureEntity pe) {
		this.pe = pe;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
