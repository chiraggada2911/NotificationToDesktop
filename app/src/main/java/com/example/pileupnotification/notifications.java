package com.example.pileupnotification;

public class notifications {

    int mId;
    String mPack;
    String mTitle;
    String mText;
    String date;



    public notifications(int mId, String mPack, String mTitle, String mText, String date) {
        this.mId = mId;
        this.mPack = mPack;
        this.mTitle = mTitle;
        this.mText = mText;
        this.date = date;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPack() {
        return mPack;
    }

    public void setmPack(String mPack) {
        this.mPack = mPack;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
