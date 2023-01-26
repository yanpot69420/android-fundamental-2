package com.example.submission2fundamental.builder;

import com.example.submission2fundamental.model.UserDetail;

public class UserDetailBuilder implements IUserDetailBuilder{

    private UserDetail userDetail;

    public UserDetailBuilder() {
        userDetail = new UserDetail("", "", "", "", "");
    }

    @Override
    public IUserDetailBuilder setId(String id) {
        if (id != "null")
            userDetail.setId(id);
        return this;
    }

    @Override
    public IUserDetailBuilder setName(String name) {
        if (name != "null")
            userDetail.setName(name);
        return this;
    }

    @Override
    public IUserDetailBuilder setLocation(String location) {
        if (location != "null")
            userDetail.setLocation(location);
        return this;
    }

    @Override
    public IUserDetailBuilder setCompany(String company) {
        if (company != "null")
            userDetail.setCompany(company);
        return this;
    }

    @Override
    public IUserDetailBuilder setBlog(String blog) {
        if (blog != "null")
            userDetail.setBlog(blog);
        return this;
    }

    @Override
    public UserDetail build() {
        return userDetail;
    }
}
