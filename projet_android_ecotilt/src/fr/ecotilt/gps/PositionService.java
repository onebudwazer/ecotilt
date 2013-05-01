package fr.ecotilt.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import fr.ecotilt.appui.model.GeoCoord;

public class PositionService extends Service {

	private LocationManager		locationMgr			= null;

	private LocationListener	onLocationChange	= new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {

			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			Log.i("myPosition", "Voici les coordonnées de votre téléphone : "
					+ latitude + " " + longitude);

			GeoCoord myPosition = new GeoCoord();
			myPosition.setLatitude(latitude);
			myPosition.setLongitude(longitude);

//			Intent ir = new Intent(this, PositionListener.class);
//			ir.putExtra("myPosition", myPosition);
		}
	};
	
//	private final IBinder binder = (IBinder) new MyBinder();
//	@Override
//	public IBinder onBind(Intent intent) {
//		return binder;
//	}
//	public class MyBinder extends Binder {
//		public PositionService getService(){
//			return PositionService.this;
//		}
//	}

	@Override
	public void onCreate() {
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
				10000, 0, onLocationChange);
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
				0, onLocationChange);
		
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		locationMgr.removeUpdates(onLocationChange);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}