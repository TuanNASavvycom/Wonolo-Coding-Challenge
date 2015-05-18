package com.savvycom.wonolocodingchallenge.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.savvycom.wonolocodingchallenge.R;
import com.savvycom.wonolocodingchallenge.impls.GetMediaLocationListener;
import com.savvycom.wonolocodingchallenge.managers.GlobalValue;
import com.savvycom.wonolocodingchallenge.managers.InstagramApi;
import com.savvycom.wonolocodingchallenge.models.pojo.InstaLocation;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Media;
import com.savvycom.wonolocodingchallenge.utils.NetworkUtils;
import com.savvycom.wonolocodingchallenge.views.adapters.FeedAdapter;

import net.londatiga.android.instagram.InstagramUser;

import butterknife.InjectView;
import butterknife.OnClick;

public class DetailsActivity extends ButterBaseActivity {

    @InjectView(R.id.tvMessage)
    TextView mTvMessage;
    @InjectView(R.id.btnTryAgain)
    Button mBtnTryAgain;
    @InjectView(R.id.flContainer)
    FrameLayout mFlContainer;
    @InjectView(R.id.prbLoading)
    ProgressBar mPrbLoading;
    @InjectView(R.id.lvFeed)
    ListView mLvFeed;
    @InjectView(R.id.llMessage)
    LinearLayout mLlMessage;

    private static final String TAG = DetailsActivity.class.getName();
    private FeedAdapter mFeedAdapter;
    private InstaLocation instaLocation;
    private InstagramUser instagramUser;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_details;
    }

    @Override
    protected void initUIControl(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getDataFromIntent();
        instagramUser = GlobalValue.getInstance().getInstagramUser();
        if (instagramUser == null) return;
        if (NetworkUtils.isOn(this)) {
            getListMediaFromLocation();
        } else {
            mLlMessage.setVisibility(View.VISIBLE);
            mPrbLoading.setVisibility(View.GONE);
            mLvFeed.setVisibility(View.GONE);
        }
    }

    private void getListMediaFromLocation() {
        try {
            getListMediaFromLocation(instaLocation.getId(), instagramUser.getAccessToken());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected boolean displayHomeAsUp() {
        return true;
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.wonolo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataFromIntent() {
        if (getIntent() != null) {
            instaLocation = getIntent().getExtras().getParcelable(InstaLocation.TAG);
            if (instaLocation == null) return;
            toolbar.setTitle(instaLocation.getName());
        }
    }

    /**
     * Get a list of recent media objects from a given location
     *
     * @param locationId
     * @param token
     */
    private void getListMediaFromLocation(String locationId, String token) {
        mLvFeed.setVisibility(View.GONE);
        mLlMessage.setVisibility(View.GONE);
        mPrbLoading.setVisibility(View.VISIBLE);
        try {
            InstagramApi.getInstance().getListMediaFromLocation(locationId, token, new GetMediaLocationListener() {
                @Override
                public void onCompleted(Media media) {
                    Log.i(TAG, "onCompleted");
                    if (media.getmData() != null && media.getmData().size() > 0) {
                        mFeedAdapter = new FeedAdapter(context, media.getmData());
                        mBtnTryAgain.setVisibility(View.GONE);
                        mLlMessage.setVisibility(View.GONE);
                        mPrbLoading.setVisibility(View.GONE);
                        mLvFeed.setVisibility(View.VISIBLE);
                        mLvFeed.setAdapter(mFeedAdapter);
                    } else {
                        mTvMessage.setText(getString(R.string.msg_no_data));
                        mLlMessage.setVisibility(View.VISIBLE);
                        mPrbLoading.setVisibility(View.GONE);
                        mLvFeed.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailed(Exception ex) {
                    mTvMessage.setText(getString(R.string.msg_no_network_connection));
                    mBtnTryAgain.setVisibility(View.GONE);
                    mLlMessage.setVisibility(View.VISIBLE);
                    mPrbLoading.setVisibility(View.GONE);
                    mLvFeed.setVisibility(View.GONE);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @OnClick(R.id.btnTryAgain)
    public void onTryAgain(){
        getListMediaFromLocation();
    }
}
