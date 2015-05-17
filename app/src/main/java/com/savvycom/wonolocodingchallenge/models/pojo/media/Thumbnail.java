package com.savvycom.wonolocodingchallenge.models.pojo.media;



public class Thumbnail{

    public static final String FIELD_HEIGHT = "height";
    public static final String FIELD_URL = "url";
    public static final String FIELD_WIDTH = "width";


    private int mHeight;
    private String mUrl;
    private int mWidth;


    public Thumbnail(){

    }

    public Thumbnail(int mHeight, String mUrl, int mWidth) {
        this.mHeight = mHeight;
        this.mUrl = mUrl;
        this.mWidth = mWidth;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getWidth() {
        return mWidth;
    }


}