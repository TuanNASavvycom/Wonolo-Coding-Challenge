package com.savvycom.wonolocodingchallenge.models.pojo.media;



public class User{

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_ID = "id";
    public static final String FIELD_FULL_NAME = "full_name";
    public static final String FIELD_PROFILE_PICTURE = "profile_picture";

    private String mUsername;
    private String mId;
    private String mFullName;
    private String mProfilePicture;

    public User(String mUsername, String mId, String mFullName, String mProfilePicture) {
        this.mUsername = mUsername;
        this.mId = mId;
        this.mFullName = mFullName;
        this.mProfilePicture = mProfilePicture;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmProfilePicture() {
        return mProfilePicture;
    }

    public void setmProfilePicture(String mProfilePicture) {
        this.mProfilePicture = mProfilePicture;
    }
}