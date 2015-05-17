package com.savvycom.wonolocodingchallenge.managers;

import net.londatiga.android.instagram.InstagramSession;
import net.londatiga.android.instagram.InstagramUser;

/**
 * Created by Tuan on 5/17/2015.
 */
public class GlobalValue {
    public static GlobalValue mGlobalValue;
    private InstagramUser instagramUser;
    private InstagramSession instagramSession;
    public static GlobalValue getInstance() {
        if (mGlobalValue == null) {
            synchronized (GlobalValue.class) {
                if (mGlobalValue == null) {
                    mGlobalValue = new GlobalValue();
                }
            }
        }
        return mGlobalValue;
    }

    public InstagramUser getInstagramUser() {
        return instagramUser;
    }

    public void setInstagramUser(InstagramUser instagramUser) {
        this.instagramUser = instagramUser;
    }

    public InstagramSession getInstagramSession() {
        return instagramSession;
    }

    public void setInstagramSession(InstagramSession instagramSession) {
        this.instagramSession = instagramSession;
    }
}
