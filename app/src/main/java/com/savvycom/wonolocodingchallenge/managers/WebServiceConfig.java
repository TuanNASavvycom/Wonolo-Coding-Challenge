package com.savvycom.wonolocodingchallenge.managers;

/**
 * Created by Tuan on 5/16/2015.
 */
public class WebServiceConfig {
    public static String SEARCH_LOCATION_BY_GEO = "https://api.instagram.com/v1/locations/search?lat=%s&lng=%s&distance="+GlobalConfig.INSTAGRAM_SEARCH_DISTANCE+"&access_token=%s";
    public static String GET_LIST_MEDIA_BY_LOCATION = "https://api.instagram.com/v1/locations/%s/media/recent?access_token=%s";
    public static String GET_LIST_MEDIA_URL = "https://api.instagram.com/v1/locations/501698815/media/recent?access_token=1931291267.8978415.8d0be0dd09404121baaf37b4d6b08e33";

}
