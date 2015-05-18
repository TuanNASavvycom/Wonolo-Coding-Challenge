package com.savvycom.wonolocodingchallenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savvycom.wonolocodingchallenge.R;
import com.savvycom.wonolocodingchallenge.managers.GlobalConfig;
import com.savvycom.wonolocodingchallenge.managers.GlobalValue;
import com.savvycom.wonolocodingchallenge.utils.NetworkUtils;
import com.savvycom.wonolocodingchallenge.utils.ToastUtils;

import net.londatiga.android.instagram.Instagram;
import net.londatiga.android.instagram.InstagramSession;
import net.londatiga.android.instagram.InstagramUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends ActionBarActivity {

    @InjectView(R.id.tvLogin)
    TextView mTvLogin;
    @InjectView(R.id.btnLogin)
    RelativeLayout mBtnLogin;

    private static final String TAG = LoginActivity.class.getName();
    public static final String INSTAGRAM_USER = "INSTAGRAM_USER";

    private Context mContext;
    private InstagramSession mInstagramSession;
    private Instagram mInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstagram = new Instagram(this, GlobalConfig.INSTAGRAM_CLIENT_ID, GlobalConfig.INSTAGRAM_CLIENT_SECRET, GlobalConfig.INSTAGRAM_REDIRECT_URI);
        mInstagramSession = mInstagram.getSession();
        if (mInstagramSession.isActive()) {
            GlobalValue.getInstance().setInstagramSession(mInstagramSession);
            GlobalValue.getInstance().setInstagramUser(mInstagramSession.getUser());
            startActivity(MainActivity.class);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.inject(this);
        }
        mContext = getApplicationContext();
    }

    private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
        @Override
        public void onSuccess(InstagramUser user) {
            GlobalValue.getInstance().setInstagramSession(mInstagramSession);
            GlobalValue.getInstance().setInstagramUser(mInstagramSession.getUser());
            startActivity(MainActivity.class);
            finish();
        }

        @Override
        public void onError(String error) {
            ToastUtils.quickToast(mContext, error);
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "onCancel");
        }
    };

    @OnClick(R.id.btnLogin)
    public void onLogin() {
        if(NetworkUtils.isOn(this)){
            try {
                mInstagram.authorize(mAuthListener);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else{
            ToastUtils.quickToast(this,getString(R.string.msg_no_network_connection));
        }
    }

    /**
     * Start Activity
     * @param clazz
     */
    private void startActivity(Class clazz) {
        try {
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
