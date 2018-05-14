package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class Franchise {

    private String ID,Thumbnail, SFCount;


    public Franchise(String id, String thumbnail,String sfcount)
    {

        Thumbnail=thumbnail;
        ID=id;
        SFCount=sfcount;
    }

    public String getSFCount() {
        return SFCount;
    }

    public void setSFCount(String sfcount) {
        SFCount = sfcount;
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
