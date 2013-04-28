package fr.ecotilt.activity.asyntask;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Pompe;

public class AsyncTaskPompe extends AsyncTask<URL, Integer, Long> {

	private List<Pompe> listPompe = new ArrayList<Pompe>();

	private ITaskCompletedPompe listener;
	
    public AsyncTaskPompe(ITaskCompletedPompe listener){
        this.listener=listener;
    }
	
	protected Long doInBackground(URL... urls) {
		
			StringBuilder content = CallWebService.getInstance().getContent("wspompe?p=0");
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayList<Pompe> membersWrapper;
			try {
				membersWrapper = mapper.readValue(content.toString(), new TypeReference<ArrayList<Pompe>>() {});
				listPompe.addAll(membersWrapper);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	protected void onProgressUpdate(Integer... progress) {
		// setProgressPercent(progress[0]);
	}
	
	protected void onPostExecute(Long result) {
		 listener.onTaskCompleted(listPompe);
	}
}
