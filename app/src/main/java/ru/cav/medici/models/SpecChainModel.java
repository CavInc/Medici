package ru.cav.medici.models;

import java.sql.Time;

public class SpecChainModel {
    private int position;
    private String chain_item;
    private Time work_time;

    public SpecChainModel(int position, String chain_item, Time work_time) {
        this.position = position;
        this.chain_item = chain_item;
        this.work_time = work_time;
    }

    public int getPosition() {
        return position;
    }

    public String getChainItem() {
        return chain_item;
    }

    public Time getWorkTime() {
        return work_time;
    }
}
