package fr.ecotilt.appui.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "VELIB")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
public class Velib extends AGenericObject {

	public Velib() {
	}

	public Velib(String name, String city, int cp, GeoCoord gc, PictureEntity pe, Category category) {
		this.name = name;
		this.gc = gc;
		this.codePostal = cp;
		this.city = city;
		this.pe = pe;
		this.category = category;
	}
	
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append(this.name);
		b.append(" ");
		b.append(this.codePostal);
		b.append(" ");
		b.append(this.city);
		b.append(" ");
		b.append(this.category);
		return b.toString();
	}
}
