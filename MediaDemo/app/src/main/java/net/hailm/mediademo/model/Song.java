package net.hailm.mediademo.model;

/**
 * Created by hai_l on 25/11/2017.
 */

public class Song {
    private String data;
    private String title;
    private String artist;
    private long duration;

    public Song() {
    }

    public Song(String data, String title, String artist, long duration) {
        this.data = data;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist()
    {
        return artist;
    }

    public long getDuration() {
        return duration;
    }
}
