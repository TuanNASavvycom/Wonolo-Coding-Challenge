package com.savvycom.wonolocodingchallenge.impls;

import com.savvycom.wonolocodingchallenge.models.pojo.location.Locations;

/**
 * Created by Tuan on 5/16/2015.
 */
public interface SearchLocationByGeoListener {
    public void onCompleted(Locations locations);
    public void onFailed(Exception ex);
}
