package ru.cav.medici.models;


public class HeadChainModel {
    private int mId;
    private String mTitle;
    private String mDescription;

    public HeadChainModel(int id, String title, String description) {
        mId = id;
        mTitle = title;
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }
}
