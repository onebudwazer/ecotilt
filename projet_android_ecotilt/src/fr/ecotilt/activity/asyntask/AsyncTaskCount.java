package fr.ecotilt.activity.asyntask;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Count;

public class AsyncTaskCount extends AsyncTask<URL, Integer, Long> {

	private ITaskCompletedCount	listener;

	private Count				callBackCount;

	public AsyncTaskCount(ITaskCompletedCount listener) {
		this.listener = listener;
	}

	@Override
	protected Long doInBackground(URL... urls) {

		int count = urls.length;
		for (int i = 0; i < count; i++) {
			String content = CallWebService.getInstance().getContent(
					urls[i].toString());
			ObjectMapper mapper = new ObjectMapper();
			try {
				callBackCount = mapper.readValue(content, Count.class);
			} catch (JsonParseException e) {
				Log.e("4001", e.toString());
			} catch (JsonMappingException e) {
				Log.e("4002", e.toString());
			} catch (IOException e) {
				Log.e("4003", e.toString());
			}
		}
		return (long) 0;
	}

	protected void onProgressUpdate(Integer... progress) {
	}

	protected void onPostExecute(Long result) {
		listener.onTaskCompleted(callBackCount);
	}
}
