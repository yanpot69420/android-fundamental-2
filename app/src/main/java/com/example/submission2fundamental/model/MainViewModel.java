package com.example.submission2fundamental.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<User>> usersLiveData;
    private String urlModel = "https://api.github.com/search/users?q=yanpot69420";

    public LiveData<List<User>> getUsersLiveData(List<User> users) {
        if(usersLiveData == null) {
            usersLiveData = new MutableLiveData<>();
            setUsersLiveData(users);
        }
        return usersLiveData;
    }

    public void setUsersLiveData(List<User> users) {
        usersLiveData.setValue(users);
    }

    public String getUrlModel() {
        return urlModel;
    }

    public void setUrlModel(String urlModel) {
        this.urlModel = urlModel;
    }
}
