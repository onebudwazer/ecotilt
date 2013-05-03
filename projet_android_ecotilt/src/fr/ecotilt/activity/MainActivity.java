package fr.ecotilt.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import fr.ecotilt.activity.asyntask.CallWebService;
import fr.ecotilt.gps.PositionService;
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
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	Intent intent = new Intent(MainActivity.this, ElectriqueBorneActivity.class);
	        	startActivity(intent);
	        	Toast.makeText(getApplicationContext(),
						position + " " + id, Toast.LENGTH_SHORT).show();
	        }
	    });
	    
	    
	    CallWebService.getInstance().checkConnectivity(this);
	    
	    
	    Intent bindIntent = new Intent(MainActivity.this, PositionService.class);
	    startService(bindIntent);
	}

}

