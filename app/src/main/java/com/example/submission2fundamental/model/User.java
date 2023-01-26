package com.example.submission2fundamental.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String avatar;
    private String id;
    private String username;
    private String type;
    private String url;

    public User(String avatar, String id, String username, String type, String url) {
        this.avatar = avatar;
        this.id = id;
        this.username = username;
        this.type = type;
        this.url = url;
    }

    protected User(Parcel in) {
        avatar = in.readString();
        id = in.readString();
        username = in.readString();
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(avatar);
        parcel.writeString(id);
        parcel.writeString(username);
        parcel.writeString(type);
        parcel.writeString(url);
    }
}
