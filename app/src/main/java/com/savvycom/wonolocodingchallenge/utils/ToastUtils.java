package com.savvycom.wonolocodingchallenge.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tuan on 3/13/2015.
 */
public class ToastUtils {

    public static void quickToast(Context context, String msg) {
        quickToast(context, msg, false);
    }

    public static void quickToast(Context context, String msg, boolean isLong) {
        Toast toast = null;
        if (isLong) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
