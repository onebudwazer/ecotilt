package fr.ecotilt.activity.asyntask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecotilt.appui.model.Count;
import fr.ecotilt.appui.model.Pompe;

public class CallWebService {

	private static String URL_HTTP = "http://192.168.1.74:8080/project_mobile_webservice/";
	
	private CallWebService() {
	}

	private static class LazySingleton {
		static CallWebService	instance	= new CallWebService();
	}

	public static CallWebService getInstance() {
		return LazySingleton.instance;
	}

	public StringBuilder getContent(String url) {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URL_HTTP + url);
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
				
				return builder;
			} else {
				Log.e("Erreur Lecture du flux json", "");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public long getCountPompe() {
		StringBuilder content = getContent(URL_HTTP + "/wscount?c=pompe");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
//			Count count = mapper.readValue(content.toString(), Count.class);
			Count membersWrapper = mapper.readValue(content.toString(), new Count().getClass());
			if (membersWrapper.getStatut().equals("valid")) {
				return membersWrapper.getValue();
			} else {
				return -1;
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
