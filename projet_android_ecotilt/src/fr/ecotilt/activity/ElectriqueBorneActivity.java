package fr.ecotilt.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.ecotilt.activity.asyntask.AsyncTaskPompe;
import fr.ecotilt.activity.asyntask.ITaskCompletedPompe;
import fr.ecotilt.activity.asyntask.StaticUri;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

import android.os.AsyncTask;

public class ElectriqueBorneActivity extends Activity implements
		ITaskCompletedPompe, OnScrollListener {

	private ArrayList<String>		valuesUi	= new ArrayList<String>();

	private ArrayAdapter<String>	adapter;
	
	private int indexPage = 0;
	private int limitPage = 5;
	
	private boolean isloading = false;
	
	private AsyncTaskPompe atPompe = new AsyncTaskPompe(this);

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.electrique_borne, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_electrique_borne);
		
		try {
			indexPage = 0;
			URL[] url = new URL[]{ new URL(StaticUri.URL_HTTP + "wspompe?p=" + indexPage)};
			atPompe.execute(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		

		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setOnScrollListener(this);
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, valuesUi);
		// adapter = new StableArrayAdapter(ElectriqueBorneActivity.this,
		listview.setAdapter(adapter);
	}

	@Override
	public void onTaskCompleted(List<Pompe> listPompe) {
		for (Pompe pompe : listPompe) {
			valuesUi.add(pompe.toString());
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int loadedItems = firstVisibleItem + visibleItemCount;
		
		if((loadedItems == totalItemCount) && !isloading){
			if(atPompe != null && (atPompe.getStatus() == AsyncTask.Status.FINISHED)){
				if (indexPage <= limitPage) {
					atPompe = new AsyncTaskPompe(this);
					try {
						indexPage++;
						URL[] url = new URL[] { new URL(StaticUri.URL_HTTP
								+ "wspompe?p=" + indexPage) };
						atPompe.execute(url);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onTaskError(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
	}


}
