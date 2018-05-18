package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class userPhotos {

    public String ID, Name, City, Thumbnail, Image;

    public userPhotos(String id, String name, String city, String thumbnail, String image) {
        Name = name;
        ID=id;
        City = city;
        Thumbnail = thumbnail;
        Image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
