package com.example.submission2fundamental.model;

import androidx.lifecycle.ViewModel;

public class FollowViewModel extends ViewModel {

    private String urlFollowers;
    private String urlFollowing;

    public String getUrlFollowers() {
        return urlFollowers;
    }

    public void setUrlFollowers(String urlFollowers) {
        this.urlFollowers = urlFollowers;
    }

    public String getUrlFollowing() {
        return urlFollowing;
    }

    public void setUrlFollowing(String urlFollowing) {
        this.urlFollowing = urlFollowing;
    }
}
