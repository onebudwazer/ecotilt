package fr.ecotilt.appui.model;

import java.io.Serializable;


public class PictureEntity implements Serializable {
	 
	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 2534304516669105673L;

	private long pictureId;
	
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