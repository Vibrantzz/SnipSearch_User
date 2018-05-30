package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class UReview {

    public String UID, UName,ID, Name, City, Thumbnail, Review, Rating,RID;

    public UReview(String uid, String uname,String id, String name, String city, String thumbnail, String review, String rating,String rid) {
        Name = name;
        ID=id;
        City = city;
        Thumbnail = thumbnail;
        Review = review;
        UName = uname;
        UID=uid;
        Rating=rating;
        RID=rid;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
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

    public String getReview() {
        return Review;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public void setReview(String review) {
        Review = Review;
    }
}
