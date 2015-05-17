package com.savvycom.wonolocodingchallenge.models.pojo.media;

/**
 * Created by Tuan on 5/17/2015.
 */
public class Feed {
    public static final String FIELD_IMAGES = "images";
    public static final String FIELD_CAPTION = "caption";
    public static final String FIELD_USER = "user";
    private Image mImage;
    private User mUser;
    private Caption mCaption;

    public Feed(Image mImage, User mUser, Caption mCaption) {
        this.mImage = mImage;
        this.mUser = mUser;
        this.mCaption = mCaption;
    }

    public Image getmImage() {
        return mImage;
    }

    public void setmImage(Image mImage) {
        this.mImage = mImage;
    }

    public User getmUser() {
        return mUser;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public Caption getmCaption() {
        return mCaption;
    }

    public void setmCaption(Caption mCaption) {
        this.mCaption = mCaption;
    }
}
