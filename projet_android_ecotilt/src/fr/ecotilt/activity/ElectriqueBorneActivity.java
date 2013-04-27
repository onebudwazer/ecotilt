package fr.ecotilt.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

public class ElectriqueBorneActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_electrique_borne);
		new DownloadFilesTask().execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.electrique_borne, menu);
		return true;
	}

	private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
		
		Pompe[] listPompe;;
		

		protected Long doInBackground(URL... urls) {
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(
					"http://10.10.2.55:8081/project_mobile_webserviceA/wspompe");
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
					
					String value = builder.toString();
					ObjectMapper om = new ObjectMapper();
					//this.listPompe = om.readValue(value, Pompe[].class);
					
//					for (int i = 0; i < listPompe.length; i++) {
//						System.out.println(listPompe[i].toString());
//					}
					Log.e("Lecture du flux json", builder.toString());
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
			// showDialog("Downloaded " + result + " bytes");
		}
	}

}
