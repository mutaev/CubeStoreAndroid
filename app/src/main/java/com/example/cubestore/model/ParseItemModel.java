package com.example.cubestore.model;


public class ParseItemModel {
    String title, subtitle, imageLink,postLink;

    public ParseItemModel(String title, String subtitle, String imageLink, String postLink) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageLink = imageLink;
        this.postLink = postLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }
}