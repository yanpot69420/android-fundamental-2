package com.example.submission2fundamental.model;

import androidx.lifecycle.ViewModel;

public class FollowersViewModel extends ViewModel {

    private String urlFollowers;

    public String getUrlFollowers() {
        return urlFollowers;
    }

    public void setUrlFollowers(String urlFollowers) {
        this.urlFollowers = urlFollowers;
    }
}
