package fr.ecotilt.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import fr.ecotilt.rsc.R;

/**
 * Pour les gestion des menu
 * @author Philippe
 *
 */
public abstract class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_parameters, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				Intent intent = new Intent(BaseActivity.this, UserSettingActivity.class);
	        	startActivity(intent);
				return true;
				
			case R.id.action_maps:
				Intent i = new Intent(BaseActivity.this, CartoActivity.class);
	        	startActivity(i);
				return true;
			default:
				return false;
		}
	}
}
