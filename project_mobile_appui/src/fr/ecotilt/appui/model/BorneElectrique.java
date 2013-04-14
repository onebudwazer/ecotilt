package fr.ecotilt.appui.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "BORNE_ELECTRIQUE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
public class BorneElectrique extends AGenericObject {
	
	public BorneElectrique() {
	}

	public BorneElectrique(String name, String city, int cp, GeoCoord gc, PictureEntity pe, Category category) {
		this.name = name;
		this.gc = gc;
		this.codePostal = cp;
		this.city = city;
		this.pe = pe;
		this.category = category;
	}
}
