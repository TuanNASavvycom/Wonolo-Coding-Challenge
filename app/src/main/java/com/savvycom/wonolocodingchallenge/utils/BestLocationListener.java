package com.savvycom.wonolocodingchallenge.utils;
import android.location.Location;
import android.os.Bundle;

/**
 * Created by Tuan on 5/16/2015.
 */


public abstract class BestLocationListener {
    public abstract void onLocationUpdate(Location location, BestLocationProvider.LocationType type, boolean isFresh);
    public abstract void onLocationUpdateTimeoutExceeded(BestLocationProvider.LocationType type);
    public abstract void onStatusChanged(String provider, int status, Bundle extras);
    public abstract void onProviderEnabled(String provider);
    public abstract void onProviderDisabled(String provider);
}
