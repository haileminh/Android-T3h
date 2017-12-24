package net.hailm.jsoupdemo.model;

/**
 * Created by hai_l on 30/11/2017.
 */

public class Story {
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private String articleUrl;

    public Story() {
    }

    public Story(String title, String description, String content, String imageUrl, String articleUrl) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.imageUrl = imageUrl;
        this.articleUrl = articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    @Override
    public String toString() {
        return "Story{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                '}' + "\n";
    }
}
