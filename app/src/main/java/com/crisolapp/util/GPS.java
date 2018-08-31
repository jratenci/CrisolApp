package com.crisolapp.util;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPS extends Service implements LocationListener {
    private double Longitud,Latitud=0.0;
    private Location location;
    boolean gpsActivo;
    LocationManager locationManager;
    Context ctx;

    public GPS() {
    }

    public GPS( Context c) {
        super();
        this.ctx = c;
        getLocation();
    }

    @Override
    public IBinder onBind(Intent intent) {
     return null;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public double getLongitud() {
        return Longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    private void getLocation(){
     try {
       // Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);
        // Settings.Secure.setLocationProviderEnabled(getContentResolver(), LocationManager.GPS_PROVIDER, true);

        locationManager = (LocationManager) this.ctx.getSystemService(ctx.LOCATION_SERVICE);
          gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
         if(gpsActivo){
             locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000*60,10, this);
             location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
             this.Latitud  = location.getLatitude();
             this.Longitud = location.getLongitude();
         }else{

         }

     }catch (Exception e){}
    }

    public void turnGPSOn()
    {

        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        this.ctx.sendBroadcast(intent);

          /**
        String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        System.out.println(".........."+provider);
        if(!provider.contains("gps"))
        {
            //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            this.ctx.sendBroadcast(poke);
        }
           */

    }

    private void turnGPSOff(){
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            sendBroadcast(poke);


        }
    }
}
