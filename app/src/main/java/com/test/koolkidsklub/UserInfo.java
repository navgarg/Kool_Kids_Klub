package com.test.koolkidsklub;

public class UserInfo {

    private String mName;
    private String mEmail;
    private String mLat;
    private String mLong;

    UserInfo(String name, String email, String lat, String Long){
        mEmail = email;
        mName = name;
        mLat = lat;
        mLong = Long;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String mLat) {
        this.mLat = mLat;
    }

    public String getLong() {
        return mLong;
    }

    public void setLong(String mLong) {
        this.mLong = mLong;
    }




}
