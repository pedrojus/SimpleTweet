package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public String imageUrl;
    public String videoUrl;
    public long id;
    public User user;

    // empty constructor needed for Parceler
    public Tweet() {}

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        JSONObject entitiesJsonObject = jsonObject.getJSONObject("entities");
        if (entitiesJsonObject.has("media")) {
            JSONArray mediaArray = entitiesJsonObject.getJSONArray("media");
            String type = mediaArray.getJSONObject(0).getString("type");
            switch(type) {
                case "photo":
                    tweet.imageUrl = mediaArray.getJSONObject(0).getString("media_url_https");
            }
        }
        return tweet;
    }

    public static List<Tweet> fromJSONArray(JSONArray jsonArray) throws JSONException{
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJSON(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

    public String getFormattedTimestamp () {
        return TimeFormatter.getTimeDifference(createdAt);
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public String getCreatedAt() { return createdAt; }

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
