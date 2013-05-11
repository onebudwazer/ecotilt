package fr.ecotilt.activity.asyntask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class CallWebService {

	private CallWebService() {
	}

	private static class LazySingleton {
		static CallWebService	instance	= new CallWebService();
	}

	public static CallWebService getInstance() {
		return LazySingleton.instance;
	}

	public String getContent(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		// "http://192.168.1.74:8080/project_mobile_webservice/wspompe");
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

				return builder.toString();
			} else {
				Log.e("Erreur Lecture du flux json", String.valueOf(statusCode));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new StringBuilder().toString();
	}

	private boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public boolean checkConnectivity(Context context) {
		boolean value = isOnline(context);
		if (value) {
			// Toast.makeText(context, "connection internet",
			// Toast.LENGTH_SHORT)
			// .show();
		} else {
			Toast.makeText(context, "Vous avez besoin d'internet",
					Toast.LENGTH_SHORT).show();
		}
		return value;
	}

}
