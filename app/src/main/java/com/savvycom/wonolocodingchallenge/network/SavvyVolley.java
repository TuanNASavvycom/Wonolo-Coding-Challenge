package com.savvycom.wonolocodingchallenge.network;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.savvycom.wonolocodingchallenge.utils.BitmapLruCache;

/**
 * Created by Tuan on 5/11/2015.
 */
public class SavvyVolley {
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    public static final String TAG = "SavvyVolley";
    private SavvyVolley() {

    }

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass/8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
    }

    public static RequestQueue getmRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        }else {
            throw new IllegalStateException("Request not initialized");
        }
    }

    public static ImageLoader getmImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
