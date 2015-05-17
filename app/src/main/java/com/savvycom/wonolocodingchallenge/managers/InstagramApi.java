package com.savvycom.wonolocodingchallenge.managers;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.savvycom.wonolocodingchallenge.impls.GetMediaLocationListener;
import com.savvycom.wonolocodingchallenge.impls.SearchLocationByGeoListener;
import com.savvycom.wonolocodingchallenge.models.pojo.LocationsParser;
import com.savvycom.wonolocodingchallenge.models.pojo.MediaParser;
import com.savvycom.wonolocodingchallenge.models.pojo.location.Locations;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Media;
import com.savvycom.wonolocodingchallenge.network.SavvyVolley;

import org.json.JSONObject;

/**
 * Created by Tuan on 5/15/2015.
 */
public class InstagramApi {
    public final static String TAG = "InstagramApi";
    private static RequestQueue mRequestQueue;
    private static InstagramApi mInstagramApi;

    public InstagramApi() {
        this.mRequestQueue = SavvyVolley.getmRequestQueue();
    }

    public static InstagramApi getInstance() {
        if (mInstagramApi == null) {
            synchronized (InstagramApi.class) {
                if (mInstagramApi == null) {
                    mInstagramApi = new InstagramApi();
                }
            }
        }
        return mInstagramApi;
    }
    public String searchLocationByGeoUrl(String lat, String lon, String token) {
        return String.format(WebServiceConfig.SEARCH_LOCATION_BY_GEO, lat, lon, token);
    }

    /**
     * Locations Request
     * @param lat
     * @param lon
     * @param token
     * @param gll
     */
    public void searchLocationByGeoRequest(String lat, String lon, String token, final SearchLocationByGeoListener gll) {
        JsonObjectRequest searchLocationRequest = new JsonObjectRequest
                (Request.Method.GET, searchLocationByGeoUrl(lat, lon, token), "", new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        Locations locations = LocationsParser.getLocations(response);
                        if (locations != null){
                            gll.onCompleted(locations);
                        }else {
                            gll.onFailed(new VolleyError());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        gll.onFailed(error);
                    }
                });
        searchLocationRequest.setRetryPolicy(new DefaultRetryPolicy(
                GlobalConfig.NETWORK_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        searchLocationRequest.setTag(SavvyVolley.TAG);
        mRequestQueue.add(searchLocationRequest);
    }

    public static String getListMediaByLocationUrl(String locationId, String token) {
        return String.format(WebServiceConfig.GET_LIST_MEDIA_BY_LOCATION, locationId, token);
    }

    public void getListMediaFromLocation(String locationId, String token, final GetMediaLocationListener gml) {
        JsonObjectRequest getListMediaRequest = new JsonObjectRequest
                (Request.Method.GET, getListMediaByLocationUrl(locationId,token), "", new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, response.toString());
                        Media media = MediaParser.getMedia(response);
                        if (media != null){
                            gml.onCompleted(media);
                        }else {
                            gml.onFailed(new VolleyError());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        gml.onFailed(error);
                    }
                });
        getListMediaRequest.setRetryPolicy(new DefaultRetryPolicy(
                GlobalConfig.NETWORK_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getListMediaRequest.setTag(SavvyVolley.TAG);
        mRequestQueue.add(getListMediaRequest);
    }
}
