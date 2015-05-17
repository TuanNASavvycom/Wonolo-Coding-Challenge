package com.savvycom.wonolocodingchallenge.activities;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.savvycom.wonolocodingchallenge.R;
import com.savvycom.wonolocodingchallenge.impls.SearchLocationByGeoListener;
import com.savvycom.wonolocodingchallenge.managers.GlobalConfig;
import com.savvycom.wonolocodingchallenge.managers.GlobalValue;
import com.savvycom.wonolocodingchallenge.managers.InstagramApi;
import com.savvycom.wonolocodingchallenge.models.pojo.InstaLocation;
import com.savvycom.wonolocodingchallenge.models.pojo.location.Locations;
import com.savvycom.wonolocodingchallenge.utils.BestLocationListener;
import com.savvycom.wonolocodingchallenge.utils.BestLocationProvider;
import com.savvycom.wonolocodingchallenge.utils.GPSUtils;
import com.savvycom.wonolocodingchallenge.utils.NetworkUtils;
import com.savvycom.wonolocodingchallenge.utils.ToastUtils;

import net.londatiga.android.instagram.InstagramUser;

import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;

public class MainActivity extends ButterBaseActivity implements GoogleMap.OnCameraChangeListener,OnMarkerClickListener{
    @InjectView(R.id.mapView)
    MapView mMapView;

    private static final String TAG = "MainActivity";
    private GoogleMap googleMap;
    private InstagramUser user;
    private int first_location = 1;
    private HashMap<Marker, InstaLocation> mMarkerHasMap;
    private BestLocationProvider mBestLocationProvider;
    private BestLocationListener mBestLocationListener;
    private Marker lastOpened = null;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean displayHomeAsUp() {
        return false;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.wonolo);
    }

    @Override
    protected void initUIControl(Bundle savedInstanceState) {
        if (readyToGo()) {
            mMapView.onCreate(savedInstanceState);
            initMap();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getDataFromIntent();
        if (!GPSUtils.isOn(this)) {
            GPSUtils.displayPromptForEnablingGPS(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout){
            onLogout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        initLocation();
        mBestLocationProvider.startLocationUpdatesWithListener(mBestLocationListener);
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        initLocation();
        mBestLocationProvider.stopLocationUpdates();
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    /**
     * When change view google map
     * @param cameraPosition
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        LatLng centerPosition = cameraPosition.target;
        double latitude = centerPosition.latitude;
        double longitude = centerPosition.longitude;
        if (NetworkUtils.isOn(this)) {
            try {
                if (latitude != 0 && longitude != 0) {
                    searchLocationByGeo(latitude, longitude, user.getAccessToken());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            ToastUtils.quickToast(this,getString(R.string.msg_no_network_connection));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Event was handled by our code do not launch default behaviour.
        if (mMarkerHasMap.containsKey(marker)) {
            InstaLocation instaLocation = mMarkerHasMap.get(marker);
            if (instaLocation != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(InstaLocation.TAG, instaLocation);
                startActivity(DetailsActivity.class,bundle);
            }
        }
        return false;
    }

    protected void getDataFromIntent() {
        if (getIntent() != null) {
            user = getIntent().getParcelableExtra(LoginActivity.INSTAGRAM_USER);
            if (user == null) return;
            GlobalValue.getInstance().setInstagramUser(user);
        }
    }

    private void initMap(){
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();
        googleMap.setOnCameraChangeListener(this);
        googleMap.setOnMarkerClickListener(this);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
    }

    private void initLocation(){
        if(mBestLocationListener == null){
            mBestLocationListener = new BestLocationListener() {

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.i(TAG, "onStatusChanged PROVIDER:" + provider + " STATUS:" + String.valueOf(status));
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.i(TAG, "onProviderEnabled PROVIDER:" + provider);
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.i(TAG, "onProviderDisabled PROVIDER:" + provider);
                }

                @Override
                public void onLocationUpdateTimeoutExceeded(BestLocationProvider.LocationType type) {
                    Log.w(TAG, "onLocationUpdateTimeoutExceeded PROVIDER:" + type);
                }

                @Override
                public void onLocationUpdate(Location location, BestLocationProvider.LocationType type,
                                             boolean isFresh) {
                    sendLocationMessage(location);
                }
            };
            if(mBestLocationProvider == null){
                mBestLocationProvider = new BestLocationProvider(this, true, true, 10000, 1000, 2, 0);
            }
        }
    }

    /**
     * Send Location Message
     * @param location
     */
    private void sendLocationMessage(Location location) {
        Message msgObj = locationHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("FIRST_LOCATION", first_location);
        bundle.putDouble("LAT", location.getLatitude());
        bundle.putDouble("LON", location.getLongitude());
        msgObj.setData(bundle);
        locationHandler.sendMessage(msgObj);

    }

    private final Handler locationHandler = new Handler() {
        public void handleMessage(Message msg) {
            int flag = msg.getData().getInt("FIRST_LOCATION");
            double lat = msg.getData().getDouble("LAT");
            double lon = msg.getData().getDouble("LON");
            if (flag == 1) {
                addMyLocationMarker(lat,lon);
                searchLocationByGeo(lat,lon,user.getAccessToken());
                first_location++;
            }
        }
    };

    /**
     * Add My Location Marker
     * @param latitude
     * @param longitude
     */
    private void addMyLocationMarker(double latitude,double longitude){
        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude,longitude));
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        // adding marker
        googleMap.addMarker(marker);
        zoomToPosition(latitude, longitude);
    }

    /**
     * Add Instagram Marker
     * @param locations
     */
    private void addInstagramMarker(Locations locations) {
        if (mMarkerHasMap == null) {
            mMarkerHasMap = new HashMap<Marker, InstaLocation>();
        } else {
            for (Marker marker : mMarkerHasMap.keySet()) {
                marker.remove();
            }
            mMarkerHasMap.clear();
        }
        List<InstaLocation> instaLocations = locations.getInstaLocations();
        if (instaLocations == null || instaLocations.size() == 0) return;
        googleMap.clear();
        for (InstaLocation instaLocation : instaLocations) {
            MarkerOptions markerOption = new MarkerOptions().position(new LatLng(
                    instaLocation.getLat(),
                    instaLocation.getLon()));
            markerOption.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.instagram_marker));
            markerOption.title(instaLocation.getName());
            Marker currentMarker = googleMap
                    .addMarker(markerOption);
            mMarkerHasMap.put(currentMarker,
                    instaLocation);
        }
    }

    /**
     * Zoom To Position by latitude, longitude
     * @param latitude
     * @param longitude
     */
    private void zoomToPosition(double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(GlobalConfig.ZOOM_LEVEL).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    /**
     * Search for a location by geographic coordinate.
     * @param lat
     * @param lon
     * @param token
     */
    public void searchLocationByGeo(double lat,double lon,String token) {
        try {
            InstagramApi.getInstance().searchLocationByGeoRequest(String.valueOf(lat), String.valueOf(lon), token, new SearchLocationByGeoListener() {
                @Override
                public void onCompleted(Locations locations) {
                    Log.i(TAG, "onCompleted");
                    addInstagramMarker(locations);
                }

                @Override
                public void onFailed(Exception ex) {
                    Log.i(TAG, ex.toString());
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Logout - Reset Instagram Session
     */
    public void onLogout(){
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        final String message = "Are you sure ?";
        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                GlobalValue.getInstance().getInstagramSession().reset();
                                startActivity(LoginActivity.class);
                                finish();
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }
}
