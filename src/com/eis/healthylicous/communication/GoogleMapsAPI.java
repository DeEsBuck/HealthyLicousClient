package com.eis.healthylicous.communication;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.eis.healthylicous.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class GoogleMapsAPI extends MapActivity {
	
	@Override
	protected void onPause() {
		mMyOverlay.disableMyLocation();
		mMapView.getOverlays().remove(mMyOverlay);
		mLocationManager.removeUpdates(mMyOverlay);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mLocationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 
				5000L, 
				500.0f, 
				mMyOverlay);
		
		mMapView.getOverlays().add(mMyOverlay);
		mMyOverlay.enableMyLocation();
		
		mMyOverlay.runOnFirstFix(new Runnable() {
			
			@Override
			public void run() {
				mMapController.animateTo(mMyOverlay.getMyLocation());
			}
		});
	}

	MapView mMapView;
	MapController mMapController;
	
	GeoPoint mGeoPoint = new GeoPoint(40737944, -73985818);
	private LocationManager mLocationManager;
	private MyLocationOverlay mMyOverlay;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.aussenwelt_google_maps);
		setTitle(R.string.tx_aussenwelt_google_maps);
		mMapView = 
			(MapView) findViewById(R.id.mv_aussenwelt_google_maps);
		mMapView.setBuiltInZoomControls(true);
		mMapView.setSatellite(true);
		
		mMapController = mMapView.getController();
		mMapController.setCenter(mGeoPoint);
		mMapController.setZoom(18);
		
		mMyOverlay = new MyLocationOverlay(this, mMapView) {
			
			@Override
			public synchronized void onLocationChanged(Location location) {
				super.onLocationChanged(location);
				final GeoPoint meinePosition = new GeoPoint(
						(int) (location.getLatitude() * 1E6), 
						(int) (location.getLongitude() * 1E6)
				);
				mMapController.animateTo(meinePosition);
			}
			
		};
		mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
