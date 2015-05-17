package com.savvycom.wonolocodingchallenge.models.pojo.location;

import com.savvycom.wonolocodingchallenge.models.pojo.InstaLocation;
import com.savvycom.wonolocodingchallenge.models.pojo.Meta;

import java.util.List;

/**
 * Created by Tuan on 5/16/2015.
 */
public class Locations {
    public List<InstaLocation> instaLocations;
    public Meta meta;

    public Locations() {
    }

    public Locations(List<InstaLocation> instaLocations, Meta meta) {
        this.instaLocations = instaLocations;
        this.meta = meta;
    }

    public List<InstaLocation> getInstaLocations() {
        return instaLocations;
    }

    public void setInstaLocations(List<InstaLocation> instaLocations) {
        this.instaLocations = instaLocations;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
