package com.test.koolkidsklub;

import android.util.Log;

public class UserInfo {

    private String mName;
    private String mEmail;
    private double mLat;
    private double mLong;

    UserInfo(){

    }

    UserInfo(String name, String email, double lat, double Long){
        mEmail = email;
        mName = name;
        mLat = lat;
        mLong = Long;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public double getLat() {
        return mLat;
    }

    public double getLong() {
        return mLong;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setLat(double mLat) {
        this.mLat = mLat;
    }

    public void setLong(double mLong) {
        this.mLong = mLong;
    }
}
