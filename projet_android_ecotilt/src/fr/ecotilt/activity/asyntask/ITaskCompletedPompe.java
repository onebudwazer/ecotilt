package fr.ecotilt.activity.asyntask;

import java.util.List;

import fr.ecotilt.appui.model.Pompe;

public interface ITaskCompletedPompe {
	void onTaskCompleted(List<Pompe> listPompe);
	void onTaskError(String error);
	void onPreExecute();
}
