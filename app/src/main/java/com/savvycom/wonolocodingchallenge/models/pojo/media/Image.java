package com.savvycom.wonolocodingchallenge.models.pojo.media;



public class Image{

    public static final String FIELD_STANDARD_RESOLUTION = "standard_resolution";
    public static final String FIELD_THUMBNAIL = "thumbnail";
    public static final String FIELD_LOW_RESOLUTION = "low_resolution";


    private StandardResolution mStandardResolution;
    private Thumbnail mThumbnail;
    private LowResolution mLowResolution;


    public Image(){

    }

    public Image(StandardResolution mStandardResolution, Thumbnail mThumbnail, LowResolution mLowResolution) {
        this.mStandardResolution = mStandardResolution;
        this.mThumbnail = mThumbnail;
        this.mLowResolution = mLowResolution;
    }

    public void setStandardResolution(StandardResolution standardResolution) {
        mStandardResolution = standardResolution;
    }

    public StandardResolution getStandardResolution() {
        return mStandardResolution;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public void setLowResolution(LowResolution lowResolution) {
        mLowResolution = lowResolution;
    }

    public LowResolution getLowResolution() {
        return mLowResolution;
    }


}