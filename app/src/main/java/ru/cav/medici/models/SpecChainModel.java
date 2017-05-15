package ru.cav.medici.models;

import java.sql.Time;

public class SpecChainModel {
    private int position;
    private String chain_item;
    private String chain_desc;
    private int work_time;

    public SpecChainModel(int position, String chain_item, int work_time) {
        this.position = position;
        this.chain_item = chain_item;
        this.work_time = work_time;
    }

    public SpecChainModel(int position, String chain_item, String chain_desc, int work_time) {
        this.position = position;
        this.chain_item = chain_item;
        this.chain_desc = chain_desc;
        this.work_time = work_time;
    }

    public int getPosition() {
        return position;
    }

    public int getId(){
        return position;
    }

    public String getChainItem() {
        return chain_item;
    }

    public int getWorkTime() {
        return work_time;
    }

    public String getChain_desc() {
        return chain_desc;
    }
}
