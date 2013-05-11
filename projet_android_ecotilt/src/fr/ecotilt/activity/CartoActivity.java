package fr.ecotilt.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

	private static LatLng		MY_POSITION				= new LatLng(46.103709,
																2.559814);
	private Marker				myMarkerPosition;

	private final int			MARKER_UPDATE_INTERVAL	= 2000;

	private Handler				handler					= new Handler();

	private Runnable			updateMarker			= new Runnable() {
															@Override
															public void run() {
																myMarkerPosition
																		.remove();
																myMarkerPosition = googleMap
																		.addMarker(new MarkerOptions()
																				.position(MY_POSITION));

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

		if (googleMap != null) {
			CameraUpdate center = CameraUpdateFactory.newLatLng(MY_POSITION);
			CameraUpdate zoom = CameraUpdateFactory.zoomTo(9);
			googleMap.moveCamera(center);
			googleMap.animateCamera(zoom);
			myMarkerPosition = googleMap.addMarker(new MarkerOptions()
					.position(MY_POSITION).title("Ma position")
					.snippet("position approximative"));
			handler.postDelayed(updateMarker, MARKER_UPDATE_INTERVAL);
		}
		// Bundle objBundle = this.getIntent().getExtras();
		// int id = objBundle.getInt("listObject");
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("http://maps.google.com/maps?daddr=" + 0 + "," + 0));
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
		Toast.makeText(CartoActivity.this, "Service is disconnected",
				Toast.LENGTH_SHORT).show();
		mBound = false;
		mService = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Toast.makeText(CartoActivity.this, "Service is connected",
				Toast.LENGTH_SHORT).show();
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
