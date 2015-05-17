package com.savvycom.wonolocodingchallenge.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.savvycom.wonolocodingchallenge.R;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Caption;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Feed;
import com.savvycom.wonolocodingchallenge.models.pojo.media.Image;
import com.savvycom.wonolocodingchallenge.models.pojo.media.User;
import com.savvycom.wonolocodingchallenge.utils.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tuan on 5/17/2015.
 */
public class FeedAdapter extends ArrayAdapter<Feed> {



    private static class ViewHolder {
        public final LinearLayout rootView;
        public final RelativeLayout header;
        public final ImageView imgAvata;
        public final TextView tvUsername;
        public final ImageView imgFeed;
        public final TextView tvCaption;
        public final ImageButton btnLike;
        public final ImageButton btnComments;
        public final ImageButton btnMore;
        public final TextView tvLikesCounter;

        private ViewHolder(LinearLayout rootView, RelativeLayout header, ImageView imgAvata, TextView tvUsername, ImageView imgFeed, TextView tvCaption, ImageButton btnLike, ImageButton btnComments, ImageButton btnMore, TextView tvLikesCounter) {
            this.rootView = rootView;
            this.header = header;
            this.imgAvata = imgAvata;
            this.tvUsername = tvUsername;
            this.imgFeed = imgFeed;
            this.tvCaption = tvCaption;
            this.btnLike = btnLike;
            this.btnComments = btnComments;
            this.btnMore = btnMore;
            this.tvLikesCounter = tvLikesCounter;
        }

        public static ViewHolder create(LinearLayout rootView) {
            RelativeLayout header = (RelativeLayout)rootView.findViewById( R.id.header );
            ImageView imgAvata = (ImageView)rootView.findViewById( R.id.imgAvata );
            TextView tvUsername = (TextView)rootView.findViewById( R.id.tvUsername );
            ImageView imgFeed = (ImageView)rootView.findViewById( R.id.imgFeed );
            TextView tvCaption = (TextView)rootView.findViewById( R.id.tvCaption );
            ImageButton btnLike = (ImageButton)rootView.findViewById( R.id.btnLike );
            ImageButton btnComments = (ImageButton)rootView.findViewById( R.id.btnComments );
            ImageButton btnMore = (ImageButton)rootView.findViewById( R.id.btnMore );
            TextView tvLikesCounter = (TextView)rootView.findViewById( R.id.tvLikesCounter );
            return new ViewHolder( rootView, header, imgAvata, tvUsername, imgFeed, tvCaption, btnLike, btnComments, btnMore, tvLikesCounter );
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if ( convertView == null ) {
            View view = mInflater.inflate( R.layout.item_feed, parent, false );
            vh = ViewHolder.create( (LinearLayout)view );
            view.setTag( vh );
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        Feed feed = getItem(position);
        // TODOBind your data to the views here
        if (feed != null) {
            bindDataToViews(feed,vh);
        }
        return vh.rootView;
    }

    private void bindDataToViews(Feed feed,final ViewHolder vh) {
        User user = feed.getmUser();
        if (user != null) {
            vh.tvUsername.setText(user.getmUsername());
            Picasso.with(mContext)
                    .load(user.getmProfilePicture())
                    .placeholder(R.drawable.img_circle_placeholder)
                    .transform(new CircleTransformation())
                    .into(vh.imgAvata);
        }
        Image image = feed.getmImage();
        if (image != null) {
            Picasso.with(mContext)
                    .load(image.getStandardResolution().getUrl())
                    .fit()
                    .into(vh.imgFeed);
        }
        Caption caption = feed.getmCaption();
        if (caption != null && caption.getmText() != null && !caption.getmText().isEmpty()) {
            vh.tvCaption.setVisibility(View.VISIBLE);
            vh.tvCaption.setText(caption.getmText());
        }
    }

    private LayoutInflater mInflater;
    private Context mContext;
    // Constructors
    public FeedAdapter(Context context, List<Feed> objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from( context );
        this.mContext = context;
    }
    public FeedAdapter(Context context, Feed[] objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from( context );
        this.mContext = context;
    }
}

