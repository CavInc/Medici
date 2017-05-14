package ru.cav.medici.models;


import java.util.ArrayList;

public class HeadChainModel {
    private int mId;
    private String mTitle;
    private String mDescription;

    private ArrayList<SpecChainModel> spec_shain;

    public HeadChainModel(int id, String title, String description) {
        mId = id;
        mTitle = title;
        mDescription = description;
    }

    public HeadChainModel(int id, String title, String description, ArrayList<SpecChainModel> spec_shain) {
        mId = id;
        mTitle = title;
        mDescription = description;
        this.spec_shain = spec_shain;
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

    public ArrayList<SpecChainModel> getSpec_shain() {
        return spec_shain;
    }
}
