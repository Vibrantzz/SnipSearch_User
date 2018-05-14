package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class Spec {

    private String ID,Thumbnail;


    public Spec(String id, String thumbnail)
    {

        Thumbnail=thumbnail;
        ID=id;

    }


    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
