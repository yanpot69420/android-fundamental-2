package com.example.submission2fundamental.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
    private String avatar;
    private String id;
    private String username;
    private String followers;
    private String following;
    private Integer followersCount = 0;
    private Integer followingCount = 0;

    public User(String avatar, String id, String username, String followers, String following) {
        this.avatar = avatar;
        this.id = id;
        this.username = username;
        this.followers = followers;
        this.following = following;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    protected User(Parcel in) {
        avatar = in.readString();
        id = in.readString();
        username = in.readString();
        followers = in.readString();
        following = in.readString();
        if (in.readByte() == 0) {
            followersCount = null;
        } else {
            followersCount = in.readInt();
        }
        if (in.readByte() == 0) {
            followingCount = null;
        } else {
            followingCount = in.readInt();
        }
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

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
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
        parcel.writeString(followers);
        parcel.writeString(following);
        if (followersCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(followersCount);
        }
        if (followingCount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(followingCount);
        }
    }
}
