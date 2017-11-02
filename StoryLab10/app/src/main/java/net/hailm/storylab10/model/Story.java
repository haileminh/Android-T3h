package net.hailm.storylab10.model;

/**
 * Created by hai_l on 31/10/2017.
 */

public class Story {
    private int avatar;
    private String name;
    private String author;
    private String type;
    private String status;
    private int chapters;
    private String updateDate;
    private String uploadDate;
    private String content;

    public Story() {
    }

    public Story(int avatar, String name, String author, String type, String status, int chapters, String updateDate, String uploadDate, String content) {
        this.avatar = avatar;
        this.name = name;
        this.author = author;
        this.type = type;
        this.status = status;
        this.chapters = chapters;
        this.updateDate = updateDate;
        this.uploadDate = uploadDate;
        this.content = content;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
