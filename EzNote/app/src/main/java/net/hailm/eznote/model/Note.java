package net.hailm.eznote.model;

/**
 * Created by hai_l on 07/12/2017.
 */

public class Note {
    public static final String BG_XANH = "Xanh";
    public static final String BG_CAM = "Cam";
    public static final String BG_DO = "Do";
    public static final String BG_XANH_LA = "Xanh la";
    public static final String BG_TIM = "Tim";
    private String date;
    private String content;
    private String color;

    public Note() {
    }

    public Note(String date, String content, String color) {
        this.date = date;
        this.content = content;
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
