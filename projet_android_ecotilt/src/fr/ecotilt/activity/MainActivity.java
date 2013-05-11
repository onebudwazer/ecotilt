package fr.ecotilt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import fr.ecotilt.activity.asyntask.CallWebService;
import fr.ecotilt.gps.LocationService;
import fr.ecotilt.rsc.R;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false);
		setContentView(R.layout.activity_main);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,
						ElectriqueBorneActivity.class);
				startActivity(intent);
			}
		});

		CallWebService.getInstance().checkConnectivity(this);
		startService(new Intent(MainActivity.this, LocationService.class));
	}

	// @Override
	// protected void onDestroy() {
	// super.onDestroy();
	// stopService(new Intent(MainActivity.this, LocationService.class));
	// }
}
