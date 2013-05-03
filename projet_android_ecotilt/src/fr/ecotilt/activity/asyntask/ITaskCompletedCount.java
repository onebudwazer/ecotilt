package fr.ecotilt.activity.asyntask;

import fr.ecotilt.appui.model.Count;

public interface ITaskCompletedCount {
	void onTaskCompleted(Count count);
	void onTaskError(String error);
}
