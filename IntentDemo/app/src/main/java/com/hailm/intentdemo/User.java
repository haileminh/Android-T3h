package com.hailm.intentdemo;

import java.io.Serializable;

/**
 * Created by hai_l on 12/10/2017.
 */

// POJO: Plain old java Object
public class User implements Serializable {
    private String email;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
