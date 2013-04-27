package fr.ecotilt.activity.asyntask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Pompe;

public class AsyncTaskPompe extends AsyncTask<URL, Integer, Long> {

	private List<Pompe> listPompe = new ArrayList<Pompe>();

	private OnTaskCompleted listener;
	
    public AsyncTaskPompe(OnTaskCompleted listener){
        this.listener=listener;
    }
	
	protected Long doInBackground(URL... urls) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://192.168.1.74:8080/project_mobile_webservice/wspompe");
		try {
			HttpResponse response = client.execute(httpGet);

			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

				ObjectMapper mapper = new ObjectMapper();
				ArrayList<Pompe> membersWrapper = mapper.readValue(builder.toString(), new TypeReference<ArrayList<Pompe>>() {});
				listPompe = membersWrapper;
			} else {
				Log.e("Lecture du flux json", builder.toString());
			}
		} catch (ClientProtocolException e) {
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
