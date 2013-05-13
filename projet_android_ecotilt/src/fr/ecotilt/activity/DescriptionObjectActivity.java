package fr.ecotilt.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

public class DescriptionObjectActivity extends BaseActivity {
	
	private ImageView image;
	
	private TextView title;
	
	private TextView address;
	
	private List<Pompe> listpompe = new ArrayList<Pompe>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_description_object);
		this.image = (ImageView) findViewById(R.id.imageView1);
		this.title = (TextView) findViewById(R.id.textView2);
		this.address = (TextView) findViewById(R.id.adress);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle bundle = getIntent().getExtras();
		
		if (bundle.getSerializable("instancePompe") != null) {
			Pompe pe = (Pompe) bundle.getSerializable("instancePompe");
			listpompe.add(pe);
			Log.e("instancePompe.getName()", pe.toString());
			byte[] blob = pe.getPictureEntity().getImage();
			
			Bitmap decodedByte = BitmapFactory.decodeByteArray(blob, 0, blob.length); 
			this.image.setImageBitmap(decodedByte);
			
			this.title.setText(pe.getName());
			String newLine = System.getProperty("line.separator");
			this.address.setText(newLine + "Adresse: adresse 1" + newLine +  
								 "Ville: " + pe.getCity() + newLine + 
								 "Code postal: " + pe.getCodePostal() + newLine + newLine +  
					             "Coordonnées: " + pe.getGeoCoord().toString());
		}

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				Intent intent = new Intent(DescriptionObjectActivity.this, UserSettingActivity.class);
	        	startActivity(intent);
				return true;
				
			case R.id.action_maps:
				Intent i = new Intent(DescriptionObjectActivity.this, CartoActivity.class);
				PompeList pompelist = new PompeList(this.listpompe);
				i.putExtra("listPompe", pompelist);
	        	startActivity(i);
				return true;
				
			case android.R.id.home:
				// This is called when the Home (Up) button is pressed
				Intent parentActivityIntent = new Intent(this, MainActivity.class);
				parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						 					| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(parentActivityIntent);
				finish();
				return true;
				
			default:
				return false;
		}
	}
}
