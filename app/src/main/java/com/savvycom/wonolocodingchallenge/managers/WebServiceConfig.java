package com.savvycom.wonolocodingchallenge.managers;

/**
 * Created by Tuan on 5/16/2015.
 */
public class WebServiceConfig {
    public static String SEARCH_LOCATION_BY_GEO = "https://api.instagram.com/v1/locations/search?lat=%s&lng=%s&distance="+GlobalConfig.INSTAGRAM_SEARCH_DISTANCE+"&access_token=%s";
    public static String GET_LIST_MEDIA_BY_LOCATION = "https://api.instagram.com/v1/locations/%s/media/recent?access_token=%s";
}
