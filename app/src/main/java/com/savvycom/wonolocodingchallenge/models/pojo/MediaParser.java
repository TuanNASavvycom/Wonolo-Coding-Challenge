package com.savvycom.wonolocodingchallenge.models.pojo;

import com.savvycom.wonolocodingchallenge.models.pojo.media.Caption;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Feed;
import com.savvycom.wonolocodingchallenge.models.pojo.media.From;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Image;
import com.savvycom.wonolocodingchallenge.models.pojo.media.LowResolution;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Media;
import com.savvycom.wonolocodingchallenge.models.pojo.media.StandardResolution;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Thumbnail;
import com.savvycom.wonolocodingchallenge.models.pojo.media.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuan on 5/17/2015.
 */
public class MediaParser {
    public static Media getMedia(JSONObject jsonObject) {
        Media media = null;
        List<Feed> datumList = new ArrayList<Feed>();
        if (jsonObject == null) return null;
        try {
            int code = jsonObject.optJSONObject(Media.FIELD_META).optInt(Meta.FIELD_CODE);
            if (code != 200) return null;
            JSONArray data = jsonObject.optJSONArray(Media.FIELD_DATA);
            if(data == null) return null;
            for (int i = 0; i < data.length(); i++) {
                JSONObject datumObject = data.optJSONObject(i);
                JSONObject imageObject = datumObject.optJSONObject(Feed.FIELD_IMAGES);
                JSONObject captionObject = datumObject.optJSONObject(Feed.FIELD_CAPTION);
                JSONObject userObject = datumObject.optJSONObject(Feed.FIELD_USER);

                Image image = getImages(imageObject);
                Caption caption = getCaption(captionObject);
                User user = getUser(userObject);
                datumList.add(new Feed(image, user, caption));
            }
            media = new Media(new Meta(code), datumList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return media;
    }

    public static Image getImages(JSONObject imageObject) {
        Image image = null;
        LowResolution lowResolution = null;
        Thumbnail thumbnail = null;
        StandardResolution standardResolution = null;
        if (imageObject == null) return null;
        try {
            JSONObject lowObject = imageObject.optJSONObject(Image.FIELD_LOW_RESOLUTION);
            if (lowObject != null) {
                String low_url = lowObject.optString(LowResolution.FIELD_URL);
                int low_width = lowObject.optInt(LowResolution.FIELD_WIDTH);
                int low_height = lowObject.optInt(LowResolution.FIELD_HEIGHT);
                lowResolution = new LowResolution(low_height, low_url, low_width);
            }
            JSONObject thumbObject = imageObject.optJSONObject(Image.FIELD_THUMBNAIL);
            if (thumbnail != null) {
                String thumb_url = thumbObject.optString(LowResolution.FIELD_URL);
                int thumb_width = thumbObject.optInt(LowResolution.FIELD_WIDTH);
                int thumb_height = thumbObject.optInt(LowResolution.FIELD_HEIGHT);
                thumbnail = new Thumbnail(thumb_height, thumb_url, thumb_width);
            }
            JSONObject standardObject = imageObject.optJSONObject(Image.FIELD_STANDARD_RESOLUTION);
            if (standardObject != null) {
                String std_url = standardObject.optString(LowResolution.FIELD_URL);
                int std_width = standardObject.optInt(LowResolution.FIELD_WIDTH);
                int std_height = standardObject.optInt(LowResolution.FIELD_HEIGHT);
                standardResolution = new StandardResolution(std_height, std_url, std_width);
            }
            image = new Image(standardResolution, thumbnail, lowResolution);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public static Caption getCaption(JSONObject captionObject) {
        if (captionObject == null) return null;
        Caption caption = null;
        try {
            String created_time = captionObject.optString(Caption.FIELD_CREATED_TIME);
            String text = captionObject.optString(Caption.FIELD_TEXT);
            JSONObject fromObject = captionObject.optJSONObject(Caption.FIELD_FROM);
            From from = null;
            //String mUsername, String mId, String mFullName, String mProfilePicture
            if (fromObject != null) {
                String username = fromObject.optString(From.FIELD_USERNAME);
                String profile_picture = fromObject.optString(From.FIELD_PROFILE_PICTURE);
                String id = fromObject.optString(From.FIELD_ID);
                String fullname = fromObject.optString(From.FIELD_FULL_NAME);
                from = new From(username, id, fullname, profile_picture);
            }
            String id = captionObject.optString(Caption.FIELD_ID);
            caption = new Caption(id, text, created_time, from);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return caption;
    }

    public static User getUser(JSONObject userObject) {
        User user = null;
        if (userObject == null) return null;
        try {
            String usename = userObject.optString(User.FIELD_USERNAME);
            String profile_picture = userObject.optString(User.FIELD_PROFILE_PICTURE);
            String id = userObject.optString(User.FIELD_ID);
            String fullname = userObject.optString(User.FIELD_FULL_NAME);
            user = new User(usename, id, fullname, profile_picture);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }
}

