package com.savvycom.wonolocodingchallenge;

import android.app.Application;

import com.savvycom.wonolocodingchallenge.network.SavvyVolley;

/**
 * Created by Tuan on 5/15/2015.
 */
public class WonoloApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initVolley();
    }

    private void initVolley() {
        SavvyVolley.init(this);
    }
}

