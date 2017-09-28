package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gretel on 9/25/17.
 */

public class User implements Parcelable{

    //List the attributes
    private String uid;
    private String name;
    private String profileImage;
    private String screenName;

    public User() {

    }

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String userProfileImage) {
        this.profileImage = userProfileImage;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String userScreenName) {
        this.screenName = userScreenName;
    }

    public static User fromJson(JSONObject json) {
        User user = new User();
        try {
            user.uid = json.getString("id_str");
            user.name = json.getString("name");
            user.profileImage = json.getString("profile_image_url");
            user.screenName = json.getString("screen_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    protected User(Parcel in) {
        uid = in.readString();
        name = in.readString();
        profileImage = in.readString();
        screenName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(profileImage);
        dest.writeString(screenName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
