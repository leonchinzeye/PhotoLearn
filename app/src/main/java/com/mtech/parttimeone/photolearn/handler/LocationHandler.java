package com.mtech.parttimeone.photolearn.handler;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.mtech.parttimeone.photolearn.activity.BaseActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by changling on 27/3/18.
 */

public class LocationHandler {
    private LocationManager locationManager;
    private Context context;

    public LocationHandler(Context context){
        this.context = context;
    }

    public Boolean checkPermission(){
        locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            int i = ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]);
            if (i != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)context, permissions, 321);
                return false;
            }
          //  Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        return true;
    }
}
