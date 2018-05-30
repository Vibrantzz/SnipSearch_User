package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 3/1/2018.
 */

public class Gallery {

    public String ID, Image;

    public Gallery(String id, String image) {
        ID = id;
        Image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
