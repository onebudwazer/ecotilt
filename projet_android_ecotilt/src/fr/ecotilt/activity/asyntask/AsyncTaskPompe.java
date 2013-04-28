package fr.ecotilt.activity.asyntask;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Pompe;

public class AsyncTaskPompe extends AsyncTask<URL, Integer, Long> {

	private List<Pompe>			listPompe	= new ArrayList<Pompe>();

	private ITaskCompletedPompe	listener;
	
	private String messageCallBack;
	
	
	public AsyncTaskPompe(ITaskCompletedPompe listener) {
		this.listener = listener;
	}

	protected Long doInBackground(URL... urls) {

		int count = urls.length;
		for (int i = 0; i < count; i++) {
			StringBuilder content = CallWebService.getInstance().getContent(
															urls[i].toString());
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Pompe> membersWrapper;
			try {
				membersWrapper = mapper.readValue(content.toString(),
							new TypeReference<ArrayList<Pompe>>() {});
				
				listPompe.addAll(membersWrapper);
				messageCallBack = "Chargement terminé";
			} catch (JsonParseException e) {
				Log.e("4001",e.toString());
				messageCallBack = e.toString();
			} catch (JsonMappingException e) {
				Log.e("4002",e.toString());
				messageCallBack = e.toString();
			} catch (IOException e) {
				Log.e("4003",e.toString());
				messageCallBack = e.toString();
			}
		}
		return (long) 0;
	}

	protected void onProgressUpdate(Integer... progress) {
		// setProgressPercent(progress[0]);
	}

	protected void onPostExecute(Long result) {
		listener.onTaskCompleted(listPompe);
		listener.onTaskError(this.messageCallBack);
	}
}
