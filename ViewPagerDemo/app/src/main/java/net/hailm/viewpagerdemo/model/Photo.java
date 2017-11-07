package net.hailm.viewpagerdemo.model;

/**
 * Created by hai_l on 03/11/2017.
 */

public class Photo {
    private int photoId;
    private String name;

    public Photo() {
    }

    public Photo(int photoId, String name) {
        this.photoId = photoId;
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
