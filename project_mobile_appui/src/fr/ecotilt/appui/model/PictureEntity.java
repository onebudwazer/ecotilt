package fr.ecotilt.appui.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "PICTURE_ENTITY")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicUpdate
public class PictureEntity {
	 
	@Id
	@GeneratedValue
	@Column(name = "PICTURE_ID")
	private long pictureId;
	
	@Column(name = "IMAGE", length=16777215)
	private byte[] image;
 
	public PictureEntity() {
	}
 
	public PictureEntity(byte[] image) {
		this.image = image;
	}
 
	public Long getpictureId() {
		return this.pictureId;
	}
 
	public void setpictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}
 
	public byte[] getImage() {
		return this.image;
	}
 
	public void setImage(byte[] image) {
		this.image = image;
	}
 
}