package fr.ecotilt.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class GpsManager  implements LocationListener  {
	
	
	@Override
	public void onLocationChanged(Location arg0) {
		
		 //affichage des valeurs dans la les zone de saisie
//	   	 mTxtViewlat.setText(" "+arg0.getLatitude());
//	   	 mTxtViewlong.setText(" "+arg0.getLongitude());
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {


	}
}
