package com.mapproject;

import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements LocationListener,
		android.location.LocationListener {
	private GoogleMap googlemap;
	double latb, lngb, latc, lngc;
	int choice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapmain);

		Intent it = this.getIntent();
		latb = it.getDoubleExtra("latb", 12.9716);  //Latitude For Bangalore
		lngb = it.getDoubleExtra("lngb", 77.5946);  //Longitude for Bangalore
		latc = it.getDoubleExtra("latc", 13.0827);  //Latitude for Chennai
		lngc = it.getDoubleExtra("lngc", 80.2707);  //Longitude for Chennai
		LatLng Bangalore = new LatLng(latb, lngb);
		LatLng Chennai = new LatLng(latc, lngc);
		choice = it.getIntExtra("Choice", 0);

		if (choice == 0) {

			int status = GooglePlayServicesUtil
					.isGooglePlayServicesAvailable(getBaseContext());
			if (status != ConnectionResult.SUCCESS) {
				int requestCode = 10;
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
						this, requestCode);
				dialog.show();
			} else {
				SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
						.findFragmentById(R.id.map);
				
				LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
				
				googlemap = fm.getMap();
				googlemap.setMyLocationEnabled(true);  //Set true to get current location
				googlemap.getUiSettings().setZoomControlsEnabled(true); //Set true to display Zoom in/Zoom out Button 
				googlemap.getUiSettings().setCompassEnabled(true); //Set true to display Compass

				

				boolean gps_enabled = false;
				boolean network_enabled = false;
				try {
					gps_enabled = locationManager
							.isProviderEnabled(LocationManager.GPS_PROVIDER); // Check if location is enabled in device
				} catch (Exception ex) {
				}

				try {
					network_enabled = locationManager
							.isProviderEnabled(LocationManager.NETWORK_PROVIDER); //Check if network is enabled in device
				} catch (Exception ex) {
				}

				if (!gps_enabled) {

					Toast.makeText(getApplicationContext(),
							"Please Enable Location Services",
							Toast.LENGTH_SHORT).show();
				} else if (!network_enabled) {
					Toast.makeText(getApplicationContext(),
							"Please Enable Network Services",
							Toast.LENGTH_SHORT).show();
				}

				Criteria criteria = new Criteria();
				String provider = locationManager.getBestProvider(criteria,
						true);
				Location location = locationManager
						.getLastKnownLocation(provider);
				
				if (location != null) {
					onLocationChanged(location);
					locationManager.requestLocationUpdates(provider, 20, 0, this);
					
					
					//double latitude = location.getLatitude();
					//double longitude = location.getLongitude();
					//LatLng latLng = new LatLng(latitude, longitude);
					//googlemap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
					//googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));
					
					googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(location.getLatitude(), location.getLongitude()), 13));
					
					CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                                                                      .zoom(15)                   // Sets the zoom
                                                                      .bearing(90)                // Sets the orientation of the camera to east
                                                                      .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                                                                      .build();                   // Creates a CameraPosition from the builder
					googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
					
				}
			}

		} else if (choice == 1) {
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			googlemap = fm.getMap();
			// googlemap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			Marker marker = googlemap.addMarker(new MarkerOptions().position(
					Bangalore).title("Bangalore"));
			googlemap.moveCamera(CameraUpdateFactory.newLatLng(Bangalore));
			googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));

		} else if (choice == 2) {
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);
			googlemap = fm.getMap();
			// googlemap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			Marker marker = googlemap.addMarker(new MarkerOptions().position(
					Chennai).title("Chennai"));
			googlemap.moveCamera(CameraUpdateFactory.newLatLng(Chennai));
			googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));
		}

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		//double latitude = location.getLatitude();
		//double longitude = location.getLongitude();
		//LatLng latLng = new LatLng(latitude, longitude);
		//googlemap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		//googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));
		
		googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 13));
		
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                                        .zoom(17)                   // Sets the zoom
                                        .bearing(90)                // Sets the orientation of the camera to east
                                        .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                                        .build();                   // Creates a CameraPosition from the builder
		googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

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
