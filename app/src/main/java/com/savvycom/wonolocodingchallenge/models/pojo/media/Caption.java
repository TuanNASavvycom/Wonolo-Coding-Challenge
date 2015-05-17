package com.savvycom.wonolocodingchallenge.models.pojo.media;



public class Caption{

    public static final String FIELD_ID = "id";
    public static final String FIELD_TEXT = "text";
    public static final String FIELD_CREATED_TIME = "created_time";
    public static final String FIELD_FROM = "from";

    public Caption(String mId, String mText, String mCreatedTime, From mFrom) {
        this.mId = mId;
        this.mText = mText;
        this.mCreatedTime = mCreatedTime;
        this.mFrom = mFrom;
    }

    private String mId;
    private String mText;
    private String mCreatedTime;
    private From mFrom;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmCreatedTime() {
        return mCreatedTime;
    }

    public void setmCreatedTime(String mCreatedTime) {
        this.mCreatedTime = mCreatedTime;
    }

    public From getmFrom() {
        return mFrom;
    }

    public void setmFrom(From mFrom) {
        this.mFrom = mFrom;
    }

}