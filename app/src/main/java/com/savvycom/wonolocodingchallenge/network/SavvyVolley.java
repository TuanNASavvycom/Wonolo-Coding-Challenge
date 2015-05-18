package com.savvycom.wonolocodingchallenge.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Tuan on 5/11/2015.
 */
public class SavvyVolley {
    private static RequestQueue mRequestQueue;
    public static final String TAG = "SavvyVolley";
    private SavvyVolley() {

    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getmRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        }else {
            throw new IllegalStateException("Request not initialized");
        }
    }
}
