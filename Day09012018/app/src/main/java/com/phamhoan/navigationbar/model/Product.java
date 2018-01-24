package com.phamhoan.navigationbar.model;

/**
 * Created by PHAMHOAN on 12/19/2017.
 */

public class Product {
    private String name;
    private String category;
    private String createdDate;
    private int iD;
    private int userID;

    public Product(String name, String category, String createdDate, int iD, int userID) {
        this.name = name;
        this.category = category;
        this.createdDate = createdDate;
        this.iD = iD;
        this.userID = userID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
