package fr.ecotilt.appui.model;

/**
 * POJO class non hibernate
 * @author Philippe
 *
 */
public class Count {

	private long value = 0;

	public Count() {
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
}
