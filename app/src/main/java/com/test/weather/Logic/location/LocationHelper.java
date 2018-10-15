package com.test.weather.Logic.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;


public class LocationHelper {
    public class LocListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {
            Log.d(TAG, "disabled");

        }
    }

    private static final LocationHelper thiz = new LocationHelper();
    private LocationManager locationManager;
    private LocListener locationListener;
    private String latitude;
    private String longitude;

    public static LocationHelper getInstance() {
        return thiz;
    }

    private LocationHelper() {
    }

    public void init(Context context) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_LOW);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        String provider = locationManager.getBestProvider(criteria, true);

        // Cant get a hold of provider
        if (provider == null) {
            Log.v(TAG, "Provider is null");
            return;
        } else {
            Log.v(TAG, "Provider: " + provider);
        }

        locationListener = new LocListener();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 1L, 1f, locationListener);

        // connect to the GPS location service
        Location oldLocation = locationManager.getLastKnownLocation(provider);

        if (oldLocation != null) {
            Log.v(TAG, "Got Old location");
            latitude = Double.toString(oldLocation.getLatitude());
            longitude = Double.toString(oldLocation.getLongitude());
        } else {
            Log.v(TAG, "NO Last Location found");
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new LocListener());


    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCity(){
        return "city";
    }
}
