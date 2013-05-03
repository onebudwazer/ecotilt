package fr.ecotilt.appui.model;


public class PictureEntity {
	 
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