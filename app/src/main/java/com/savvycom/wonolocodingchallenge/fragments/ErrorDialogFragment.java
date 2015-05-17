package com.savvycom.wonolocodingchallenge.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by Tuan on 5/13/2015.
 */
public class ErrorDialogFragment extends DialogFragment {
    static final String ARG_STATUS="status";
    public static ErrorDialogFragment newInstance(int status) {
        Bundle args=new Bundle();
        args.putInt(ARG_STATUS, status);
        ErrorDialogFragment result=new ErrorDialogFragment();
        result.setArguments(args);
        return(result);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args=getArguments();
        return GooglePlayServicesUtil.getErrorDialog(args.getInt(ARG_STATUS),
                getActivity(), 0);
    }


    @Override
    public void onDismiss(DialogInterface dlg) {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
