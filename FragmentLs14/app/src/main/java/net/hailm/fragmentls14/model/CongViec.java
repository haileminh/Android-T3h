package net.hailm.fragmentls14.model;

/**
 * Created by hai_l on 16/11/2017.
 */

public class CongViec {
    private int id;
    private String tenCV;

    public CongViec(int id, String tenCV) {
        this.id = id;
        this.tenCV = tenCV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }
}
