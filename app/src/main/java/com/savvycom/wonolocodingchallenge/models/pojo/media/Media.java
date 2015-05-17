package com.savvycom.wonolocodingchallenge.models.pojo.media;

import com.savvycom.wonolocodingchallenge.models.pojo.Meta;

import java.util.List;

/**
 * Created by Tuan on 5/17/2015.
 */
public class Media {
    public static final String FIELD_DATA = "data";
    public static final String FIELD_META = "meta";

    private Meta mMeta;
    private List<Feed> mData;

    public Media() {
    }

    public Media(Meta mMeta, List<Feed> mData) {
        this.mMeta = mMeta;
        this.mData = mData;
    }

    public Meta getmMeta() {
        return mMeta;
    }

    public void setmMeta(Meta mMeta) {
        this.mMeta = mMeta;
    }

    public List<Feed> getmData() {
        return mData;
    }

    public void setmData(List<Feed> mData) {
        this.mData = mData;
    }
}
