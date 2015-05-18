package com.savvycom.wonolocodingchallenge.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.savvycom.wonolocodingchallenge.R;
import com.savvycom.wonolocodingchallenge.fragments.ErrorDialogFragment;

import butterknife.ButterKnife;

/**
 * Created by Tuan on 3/13/2015.
 */
public abstract class ButterBaseActivity extends ActionBarActivity {

    protected static final String TAG_ERROR_DIALOG_FRAGMENT="errorDialog";
    protected Toolbar toolbar;
    protected Context context;
    protected abstract int getLayoutResource();
    protected abstract void initUIControl(Bundle savedInstanceState);
    protected abstract void initData(Bundle savedInstanceState);
    protected abstract boolean displayHomeAsUp();
    protected abstract String getToolbarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.inject(this);
        context = getApplicationContext();
        initToolbar();
        initUIControl(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * Init Toolbar
     */
    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            if (displayHomeAsUp()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
            toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
            toolbar.setTitle(getToolbarTitle());
        }
    }

    /**
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Start Activity with Bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void startActivity(Class<?> clazz, Bundle bundle) {
        try {
            Intent baseIntent = new Intent(this, clazz);
            if (bundle != null)
                baseIntent.putExtras(bundle);
            startActivity(baseIntent);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Start Activity without Bundle
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

    /*Check Google Play Service*/
    protected boolean readyToGo() {
        int status=
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (status == ConnectionResult.SUCCESS) {
            if (getVersionFromPackageManager(this) >= 2) {
                return(true);
            }
            else {
                Toast.makeText(this, R.string.no_maps, Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else if (GooglePlayServicesUtil.isUserRecoverableError(status)) {
            ErrorDialogFragment.newInstance(status)
                    .show(getSupportFragmentManager(),
                            TAG_ERROR_DIALOG_FRAGMENT);
        }
        else {
            Toast.makeText(this, R.string.no_maps, Toast.LENGTH_LONG).show();
            finish();
        }
        return(false);
    }

    /**
     * Get version from package manager
     * @param context
     * @return
     */
    private static int getVersionFromPackageManager(Context context) {
        PackageManager packageManager=context.getPackageManager();
        FeatureInfo[] featureInfos=
                packageManager.getSystemAvailableFeatures();
        if (featureInfos != null && featureInfos.length > 0) {
            for (FeatureInfo featureInfo : featureInfos) {
                // Null feature name means this feature is the open
                // gl es version feature.
                if (featureInfo.name == null) {
                    if (featureInfo.reqGlEsVersion != FeatureInfo.GL_ES_VERSION_UNDEFINED) {
                        return getMajorVersion(featureInfo.reqGlEsVersion);
                    }
                    else {
                        return 1; // Lack of property means OpenGL ES
                        // version 1
                    }
                }
            }
        }
        return 1;
    }
    /** @see FeatureInfo#getGlEsVersion() */
    private static int getMajorVersion(int glEsVersion) {
        return((glEsVersion & 0xffff0000) >> 16);
    }
}
