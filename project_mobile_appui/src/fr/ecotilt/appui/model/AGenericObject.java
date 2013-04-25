package fr.ecotilt.appui.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ecotilt.appui.util.CodePostal;

@MappedSuperclass
public abstract class AGenericObject {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int				id;

	@Column(name = "NAME")
	protected String		name;

	@Column(name = "CODE_POSTAL")
	protected int			codePostal;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "GEOCOORD_ID")
	protected GeoCoord		gc;

	@Column(name = "CITY")
	protected String		city;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PICTURE_ID")
	protected PictureEntity	pe;

	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "CATEGORY_ID", nullable = false)
	// protected Category category;

	@Column(name = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
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
		if (CodePostal.isAValidZipCode(codePostal)) {
			this.codePostal = codePostal;
		} else {
			try {
				throw new Exception("Pompe : problème zip code");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

	// public Category getCategory() {
	// return category;
	// }
	//
	// public void setCategory(Category category) {
	// this.category = category;
	// }

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
