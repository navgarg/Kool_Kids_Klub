package com.test.koolkidsklub;

import android.util.Log;

public class UserInfo {

    private String mName;
    private String mEmail;
    private String mLat;
    private String mLong;
    private String mPhone;

    UserInfo(){

    }

    UserInfo(String name, String email, String lat, String Long, String phone){
        mEmail = email;
        mName = name;
        mLat = lat;
        mLong = Long;
        mPhone = phone;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getLat() {
        return mLat;
    }

    public String getLong() {
        return mLong;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setLat(String mLat) {
        this.mLat = mLat;
    }

    public void setLong(String mLong) {
        this.mLong = mLong;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
