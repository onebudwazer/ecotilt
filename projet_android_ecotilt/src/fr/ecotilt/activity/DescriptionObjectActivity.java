package fr.ecotilt.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

public class DescriptionObjectActivity extends BaseActivity {
	
	private ImageView image;
	
	private TextView title;
	
	private TextView address;
	
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
			
			Log.e("instancePompe.getName()", pe.toString());
			byte[] blob = pe.getPictureEntity().getImage();
			
			Bitmap decodedByte = BitmapFactory.decodeByteArray(blob, 0, blob.length); 
			this.image.setImageBitmap(decodedByte);
			
			this.title.setText(pe.getName());
			
			this.address.setText(pe.getCodePostal() + " " + pe.getCity() + " " + pe.getGeoCoord().toString());
		}

	}
}
