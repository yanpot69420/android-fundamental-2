package com.example.submission2fundamental.builder;

import com.example.submission2fundamental.model.UserDetail;

public interface IUserDetailBuilder {
    IUserDetailBuilder setId(String id);
    IUserDetailBuilder setName(String name);
    IUserDetailBuilder setLocation(String location);
    IUserDetailBuilder setCompany(String company);
    IUserDetailBuilder setBlog(String blog);
    UserDetail build();
}
