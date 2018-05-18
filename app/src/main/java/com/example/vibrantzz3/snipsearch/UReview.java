package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class UReview {

    public String ID, Name, City, Thumbnail, Review, Rating,RID;

    public UReview(String id, String name, String city, String thumbnail, String review, String rating,String rid) {
        Name = name;
        ID=id;
        City = city;
        Thumbnail = thumbnail;
        Review = review;
        Rating=rating;
        RID=rid;
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
