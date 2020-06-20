package com.test.koolkidsklub;

public class MissionsInfo {

    private String mName;
    private String mShort_desc;
    private String mLong_desc;

    MissionsInfo(){

    }

    MissionsInfo(String name, String short_desc){
        mName = name;
        mShort_desc = short_desc;
    }

    MissionsInfo(String name, String long_desc, String short_desc){
        mName = name;
        mShort_desc = short_desc;
        mLong_desc = long_desc;
    }

    public String getname() {
        return mName;
    }

    public void setname(String mName) {
        this.mName = mName;
    }

    public String getShort_desc() {
        return mShort_desc;
    }

    public void setShort_desc(String mShort_desc) {
        this.mShort_desc = mShort_desc;
    }

    public String getLong_desc() {
        return mLong_desc;
    }

    public void setLong_desc(String mLong_desc) {
        this.mLong_desc = mLong_desc;
    }
}
