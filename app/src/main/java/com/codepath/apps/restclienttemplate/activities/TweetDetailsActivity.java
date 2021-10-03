package com.codepath.apps.restclienttemplate.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {
    public static final String TAG = "TweetDetailsActivity";
    ImageView ivProfileImage;
    TextView tvBody;
    TextView tvScreenName;
    TextView tvName;
    TextView tvTimestamp;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
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
    }
}