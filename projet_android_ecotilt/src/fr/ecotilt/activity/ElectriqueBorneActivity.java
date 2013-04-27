package fr.ecotilt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.ecotilt.activity.asyntask.AsyncTaskPompe;
import fr.ecotilt.activity.asyntask.ITaskCompletedPompe;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

public class ElectriqueBorneActivity extends Activity implements
		ITaskCompletedPompe {

	private ArrayList<String>		valuesUi	= new ArrayList<String>();

	private ArrayAdapter<String>	adapter;

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
		AsyncTaskPompe atPompe = new AsyncTaskPompe(this);
		atPompe.execute();

		final ListView listview = (ListView) findViewById(R.id.listview);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, valuesUi);
		// adapter = new StableArrayAdapter(ElectriqueBorneActivity.this,
		listview.setAdapter(adapter);
	}

	@Override
	public void onTaskCompleted(List<Pompe> listPompe) {
		for (Pompe pompe : listPompe) {
			valuesUi.add(pompe.getName());
		}
		adapter.notifyDataSetChanged();
	}

}
