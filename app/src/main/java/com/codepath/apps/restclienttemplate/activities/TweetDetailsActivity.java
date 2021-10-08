package com.codepath.apps.restclienttemplate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    public static final String TAG = "TweetDetailsActivity";
    ImageView ivProfileImage;
    ImageView ivImage;
    VideoView vvVideo;
    TextView tvBody;
    TextView tvScreenName;
    TextView tvName;
    TextView tvTimestamp;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher_twitter);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_tweet_details);

        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        vvVideo = (VideoView) findViewById(R.id.vvVideo);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvName = (TextView) findViewById(R.id.tvName);
        tvTimestamp = (TextView) findViewById(R.id.tvTimestamp);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d(TAG, "Showing Tweet details...");

        Glide.with(this).load(tweet.user.profileImageURL).into(ivProfileImage);
        tvBody.setText(tweet.getBody());
        tvScreenName.setText(tweet.getUser().screenName);
        tvName.setText(tweet.getUser().name);
        tvTimestamp.setText(TimeFormatter.getTimeStamp(tweet.getCreatedAt()));
        if ((tweet.videoUrl == null)) {
            vvVideo.setVisibility(vvVideo.GONE);
            Glide.with(this).load(tweet.imageUrl).into(ivImage);
            return;
        } else if ((tweet.imageUrl == null)) {
            ivImage.setVisibility(ivImage.GONE);
            vvVideo.setVideoPath(tweet.videoUrl);
            return;
        }
    }
}