package fr.ecotilt.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class asynctask extends AsyncTask<Void, Void, String> {
	private final String				mNoteItWebUrl			= "your-url.com";
	private ArrayList<NameValuePair>	mParams;
	private OnPostExecuteListener		mPostExecuteListener	= null;

	public static interface OnPostExecuteListener {
		void onPostExecute(String result);
	}

	asynctask(ArrayList<NameValuePair> nameValuePairs,
			OnPostExecuteListener postExecuteListener) throws Exception {

		mParams = nameValuePairs;
		mPostExecuteListener = postExecuteListener;
		if (mPostExecuteListener == null)
			throw new Exception("Param cannot be null.");
	}

	@Override
	protected String doInBackground(Void... params) {

		String result = "";

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(mNoteItWebUrl);

		try {
			// Add parameters
			httppost.setEntity(new UrlEncodedFormEntity(mParams));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inStream = entity.getContent();
				result = convertStreamToString(inStream);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected void onPostExecute(String result) {

	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
} // AsyncInvokeURLTask
