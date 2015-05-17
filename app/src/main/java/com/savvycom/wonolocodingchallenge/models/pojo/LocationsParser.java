package com.savvycom.wonolocodingchallenge.models.pojo;

import com.savvycom.wonolocodingchallenge.models.pojo.location.Locations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuan on 5/16/2015.
 */
public class LocationsParser {
    private static final String FIELD_DATA = "data";
    private static final String FIELD_META = "meta";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_LONGITUDE = "longitude";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_CODE = "code";

    public static Locations getLocations(JSONObject jsonObject) {
        Locations locations = null;
        List<InstaLocation> instaLocationList = new ArrayList<InstaLocation>();
        if (jsonObject == null) return null;
        try {
            int code = jsonObject.optJSONObject(FIELD_META).optInt(FIELD_CODE);
            if (code != 200) return null;
            JSONArray data = jsonObject.optJSONArray(FIELD_DATA);
            if(data == null) return null;
            for (int i = 0; i < data.length(); i++) {
                JSONObject location = data.optJSONObject(i);
                double lat = location.optDouble(FIELD_LATITUDE);
                double lon = location.optDouble(FIELD_LONGITUDE);
                String id = location.optString(FIELD_ID);
                String name = location.optString(FIELD_NAME);
                instaLocationList.add(new InstaLocation(id,lat,lon,name));
            }
            locations = new Locations(instaLocationList,new Meta(code));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return locations;
    }
}
