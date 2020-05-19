package com.zaf.exomusicplayer.model;

public class SourceListItem {

    private int icon, id, duration;
    private String name;
    private String artist;
    private String year;

    public SourceListItem(int id, int icon, String name, String artist, String year, int duration) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.duration = duration;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
