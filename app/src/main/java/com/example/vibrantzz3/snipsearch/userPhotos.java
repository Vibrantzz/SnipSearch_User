package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 2/28/2018.
 */

public class userPhotos {

    public String ID, Image, UName, SName,UID,SID;

    public userPhotos(String id, String image, String uname, String sname, String uid, String sid) {
        ID=id;
        Image = image;
        UName=uname;
        SName=sname;
        UID=uid;
        SID=sid;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
