package com.playlist.demo.model;

public class RecommendationItem {
    private String main;
    private String sub;
    private String imageUrl;

    public RecommendationItem(String main, String sub, String imageUrl){
        this.main = main;
        this.sub = sub;
        this.imageUrl = imageUrl;
    }

    public String getMain(){
        return main;
    }

    public String getSub(){
        return sub;
    }

    public String getImageUrl(){
        return imageUrl;
    }
}
