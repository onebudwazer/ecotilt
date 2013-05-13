package fr.ecotilt.activity;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.gps.ITaskLocationService;
import fr.ecotilt.gps.LocationService;
import fr.ecotilt.gps.LocationService.LocalBinder;
import fr.ecotilt.rsc.R;

public class CartoActivity extends android.support.v4.app.FragmentActivity
		implements OnClickListener, OnInfoWindowClickListener,
		ServiceConnection, ITaskLocationService {

	private SupportMapFragment	supportMapFragment;

	private GoogleMap			googleMap;

	private boolean				mBound					= false;

	private LocationService		mService;

	private static LatLng		MY_POSITION;

	private Marker				myMarkerPosition;

	private final int			MARKER_UPDATE_INTERVAL	= 6000;

	private Handler				handler					= new Handler();

	private Runnable			updateMarker			= new Runnable() {
															@Override
															public void run() {
																myMarkerPosition
																		.remove();
																myMarkerPosition = googleMap
																		.addMarker(new MarkerOptions()
																				.position(
																						MY_POSITION)
																				.title("Ma position")
																				.snippet(
																						"position approximative")
																				.icon(BitmapDescriptorFactory
																						.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
																handler.postDelayed(
																		this,
																		MARKER_UPDATE_INTERVAL);
															}
														};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		FragmentManager myFragmentManager = getSupportFragmentManager();
		supportMapFragment = (SupportMapFragment) myFragmentManager
				.findFragmentById(R.id.map);

		googleMap = supportMapFragment.getMap();

		googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		googleMap.setOnInfoWindowClickListener(this);
		googleMap.getUiSettings().setCompassEnabled(true);
		googleMap.getUiSettings().setZoomControlsEnabled(true);

		double[] value = getGPS();
		MY_POSITION = new LatLng(value[0], value[1]);

		if (googleMap != null) {
			CameraUpdate center = CameraUpdateFactory.newLatLng(MY_POSITION);
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(9);
			googleMap.moveCamera(center);
			googleMap.animateCamera(zoom);
			myMarkerPosition = googleMap.addMarker(new MarkerOptions()
					.position(MY_POSITION)
					.title("Ma position")
					.snippet("position approximative")
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
			handler.postDelayed(updateMarker, MARKER_UPDATE_INTERVAL);
		}
		// Bundle objBundle = this.getIntent().getExtras();
		// int id = objBundle.getInt("listObject");

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			PompeList li = (PompeList) bundle.getSerializable("listPompe");
			List<Pompe> pompeList = li.getList();

			for (int index = 0; index < pompeList.size(); index++) {
				Pompe instance = pompeList.get(index);
				
				LatLng latlng = new LatLng(instance.getGeoCoord().getLatitude(), 
						   instance.getGeoCoord().getLongitude());
				googleMap
						.addMarker(new MarkerOptions()
						.position(latlng)
						.title(instance.getName())
						.snippet(instance.getGeoCoord().toString()));
			}

			Log.e("ajout point pompe", "");
		}
	}

	private double[] getGPS() {
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = lm.getProviders(true);

		Location l = null;

		for (int i = providers.size() - 1; i >= 0; i--) {
			l = lm.getLastKnownLocation(providers.get(i));
			if (l != null)
				break;
		}

		double[] gps = new double[2];
		if (l != null) {
			gps[0] = l.getLatitude();
			gps[1] = l.getLongitude();
		}
		return gps;
	}

//	@Override
//	public void onInfoWindowClick(Marker arg0) {
//		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//				Uri.parse("http://maps.google.com/maps?daddr=" + 0 + "," + 0));
//		startActivity(intent);
//	}
	
	@Override
	public void onInfoWindowClick(Marker pointSelectionne) 
	{
		// Ouverture de l'application de navigation pour créer l'itinéraire vers le restaurant
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + pointSelectionne.getPosition().latitude + "," + pointSelectionne.getPosition().longitude));
		startActivity(intent);
	}
	

	@Override
	public void onClick(View arg0) {
	}

	@Override
	public void updateLocation(Location location) {
		MY_POSITION = new LatLng(location.getLatitude(),
				location.getLongitude());
		googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MY_POSITION,
				16));
	}

	protected void onStart() {
		super.onStart();
		Intent mIntent = new Intent(this, LocationService.class);
		bindService(mIntent, this, BIND_AUTO_CREATE);
	};

	@Override
	protected void onStop() {
		super.onStop();
		if (mBound) {
			mService.unregisterListener(this);
			unbindService(this);
			mBound = false;
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		mService.unregisterListener(this);
		// Toast.makeText(CartoActivity.this, "Service is disconnected",
		// Toast.LENGTH_SHORT).show();
		mBound = false;
		mService = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// Toast.makeText(CartoActivity.this, "Service is connected",
		// Toast.LENGTH_SHORT).show();
		mBound = true;
		LocalBinder mLocalBinder = (LocalBinder) service;
		mService = mLocalBinder.getServerInstance();
		mService.registerListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(updateMarker);
	}
}
