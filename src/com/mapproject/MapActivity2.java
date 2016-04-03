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

public class MapActivity2 extends FragmentActivity{
	static final LatLng Bangalore = new LatLng(12.9716,77.5946);
	private GoogleMap googlemap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainbang);
		
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
            Marker marker = googlemap.addMarker(new MarkerOptions().position(Bangalore).title("Bangalore"));
            googlemap.moveCamera(CameraUpdateFactory.newLatLng(Bangalore)); 
            googlemap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
	}

}
