package fr.ecotilt.activity.asyntask;

import java.util.List;

import fr.ecotilt.appui.model.Pompe;

public interface OnTaskCompleted {
	void onTaskCompleted(List<Pompe> listPompe);
}
