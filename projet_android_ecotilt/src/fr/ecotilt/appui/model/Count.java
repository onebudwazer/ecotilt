package fr.ecotilt.appui.model;

/**
 * POJO class non hibernate
 * @author Philippe
 *
 */
public class Count {

	private long value = 0;
	
	private String statut;

	public Count() {
	}
	
	public void setValue(long value) {
		this.value = value;
		
		if (value == -1) {
			statut = "error";
		} else {
			statut = "valid";
		}
	}
	
	public String getStatut() {
		return statut;
	}
	
	public long getValue() {
		return value;
	}
}
