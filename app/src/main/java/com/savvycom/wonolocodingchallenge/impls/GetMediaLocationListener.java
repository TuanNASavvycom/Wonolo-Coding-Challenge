package com.savvycom.wonolocodingchallenge.impls;

import com.savvycom.wonolocodingchallenge.models.pojo.media.Media;

/**
 * Created by Tuan on 5/17/2015.
 */
public interface GetMediaLocationListener {
    public void onCompleted(Media media);

    public void onFailed(Exception ex);
}
