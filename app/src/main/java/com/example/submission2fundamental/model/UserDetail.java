package com.example.submission2fundamental.model;

public class UserDetail {
    private String id;
    private String name;
    private String location;
    private String company;
    private String blog;

    public UserDetail() {
    }

    public UserDetail(String id, String name, String location, String company, String blog) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.company = company;
        this.blog = blog;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
