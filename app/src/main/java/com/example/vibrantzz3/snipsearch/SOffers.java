package com.example.vibrantzz3.snipsearch;

/**
 * Created by Vibrantzz3 on 3/1/2018.
 */

public class SOffers {

    public String Offer, Period;

    public SOffers(String offer, String period) {
        Offer = offer;
        Period = period;
    }

    public String getOffer() {
        return Offer;
    }

    public void setOffer(String offer) {
        Offer = offer;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }
}
