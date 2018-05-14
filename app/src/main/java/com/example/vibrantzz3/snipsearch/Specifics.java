package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/15/2018.
 */

public class Specifics {

    private String Name, Location, Rating,Thumbnail,ID, UID;


    public Specifics(String name, String location, String rating, String thumbnail, String id, String uid)
    {
        Name=name;
        Location=location;
        Rating=rating;
        Thumbnail=thumbnail;
        ID=id;
        UID=uid;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
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
