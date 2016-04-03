package com.mapproject;

import android.app.Activity;
import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity1 extends FragmentActivity implements LocationListener, android.location.LocationListener{
	private GoogleMap googlemap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapmain);
		
		 int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
	        if(status!=ConnectionResult.SUCCESS){
	        	int requestCode = 10;
	            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	            dialog.show();
	            }
	        else
	        {
	        	SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
	            googlemap = fm.getMap();
	            googlemap.setMyLocationEnabled(true);
	            LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
	            Criteria criteria = new Criteria();
	            String provider = locationManager.getBestProvider(criteria, true);
	            Location location = locationManager.getLastKnownLocation(provider);
	            if(location!=null){
	                onLocationChanged(location);
	                locationManager.requestLocationUpdates(provider, 20000, 0, this);
	            }
	        }
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
