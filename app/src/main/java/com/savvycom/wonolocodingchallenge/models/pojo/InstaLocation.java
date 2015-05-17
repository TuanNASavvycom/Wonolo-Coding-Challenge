package com.savvycom.wonolocodingchallenge.models.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tuan on 5/16/2015.
 */
public class InstaLocation implements Parcelable {
    public static final String TAG = InstaLocation.class.getName();
    public String id;
    public double lat;
    public double lon;
    public String name;

    public InstaLocation() {
    }

    public InstaLocation(String id, double lat, double lon, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
        dest.writeString(this.name);
    }

    private InstaLocation(Parcel in) {
        this.id = in.readString();
        this.lat = in.readDouble();
        this.lon = in.readDouble();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<InstaLocation> CREATOR = new Parcelable.Creator<InstaLocation>() {
        public InstaLocation createFromParcel(Parcel source) {
            return new InstaLocation(source);
        }

        public InstaLocation[] newArray(int size) {
            return new InstaLocation[size];
        }
    };
}
