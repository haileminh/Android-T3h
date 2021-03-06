package com.hailm.recyclerview.model;

/**
 * Created by hai_l on 26/10/2017.
 */

public class User {
    private String avatarUrl;
    private String name;
    private String email;

    public User() {
    }

    public User(String avatarUrl, String name, String email) {
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
