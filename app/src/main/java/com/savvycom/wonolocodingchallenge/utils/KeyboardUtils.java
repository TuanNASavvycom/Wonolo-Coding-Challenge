package com.savvycom.wonolocodingchallenge.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Tuan on 3/18/2015.
 */
public class KeyboardUtils {

    public static void showKeyboard(View theView) {
        Context context = theView.getContext();
        Object service = context.getSystemService(Context.INPUT_METHOD_SERVICE);

        InputMethodManager imm = (InputMethodManager) service;
        if (imm != null) {
            imm.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }

    public static void hideKeyboard(View theView) {
        Context context = theView.getContext();
        Object service = context.getSystemService(Context.INPUT_METHOD_SERVICE);

        InputMethodManager imm = (InputMethodManager) service;
        if (imm != null) {
            imm.hideSoftInputFromWindow(theView.getWindowToken(), 0);
        }
    }

    /*
    ** Hide keyboard on touch outside of textfield
    private Rect mRect = new Rect();
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);

        int[] location = new int[2];
        edtSearch.getLocationOnScreen(location);
        mRect.left = location[0];
        mRect.top = location[1];
        mRect.right = location[0] + edtSearch.getWidth();
        mRect.bottom = location[1] + edtSearch.getHeight();

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        if (action == MotionEvent.ACTION_DOWN && !mRect.contains(x, y)) {
            InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }*/
}