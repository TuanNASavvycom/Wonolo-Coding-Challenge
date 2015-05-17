package com.savvycom.wonolocodingchallenge.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.savvycom.wonolocodingchallenge.impls.OnFragmentInteractionListener;

import butterknife.ButterKnife;

/**
 * Created by Tuan on 3/13/2015.
 */
public abstract class ButterBaseFragment extends Fragment {
    protected OnFragmentInteractionListener mListener;
    protected abstract int getLayoutResource();
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * Start Activity with Bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Start Activity without Bundle
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }
}
