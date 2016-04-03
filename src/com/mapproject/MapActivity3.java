package com.mapproject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MapActivity3 extends FragmentActivity {
	static final LatLng Chennai = new LatLng(13.0827,80.2707);
	private GoogleMap googlemap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapchen);
		
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
            //googlemap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker marker = googlemap.addMarker(new MarkerOptions().position(Chennai).title("Chennai"));
            googlemap.moveCamera(CameraUpdateFactory.newLatLng(Chennai)); 
            googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
	}

}
