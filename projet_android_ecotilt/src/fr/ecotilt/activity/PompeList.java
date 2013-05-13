package fr.ecotilt.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.ecotilt.appui.model.Pompe;

public class PompeList implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -1517356769379930541L;
	private List<Pompe>			innerContacts = new ArrayList<Pompe>();

	public PompeList(List<Pompe> pompes) {
		this.innerContacts = pompes;
	}

	public List<Pompe> getList() {
		return this.innerContacts;
	}
	
}