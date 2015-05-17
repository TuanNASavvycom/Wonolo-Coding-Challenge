package com.savvycom.wonolocodingchallenge.models.pojo.media;



public class From{

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_ID = "id";
    public static final String FIELD_FULL_NAME = "full_name";
    public static final String FIELD_PROFILE_PICTURE = "profile_picture";


    private String mUsername;
    private String mId;
    private String mFullName;
    private String mProfilePicture;


    public From(){

    }

    public From(String mUsername, String mId, String mFullName, String mProfilePicture) {
        this.mUsername = mUsername;
        this.mId = mId;
        this.mFullName = mFullName;
        this.mProfilePicture = mProfilePicture;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof From){
            return ((From) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ((String)mId).hashCode();
    }

}