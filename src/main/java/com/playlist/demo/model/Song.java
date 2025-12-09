package com.playlist.demo.model;

public class Song {
    private String artist;
    private String title;
    private String imageUrl;

    public Song(String artist, String title, String imageUrl) {
        this.artist = artist;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }
    public String getTitle(){
        return title;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}
