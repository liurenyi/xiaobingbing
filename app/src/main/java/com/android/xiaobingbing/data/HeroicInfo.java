package com.android.xiaobingbing.data;

import org.litepal.crud.DataSupport;

/**
 * Created by liurenyi on 2017/5/12.
 */
public class HeroicInfo extends DataSupport {

    private int id;
    private String heroicId;

    public String getHeroicId() {
        return heroicId;
    }

    public void setHeroicId(String heroicId) {
        this.heroicId = heroicId;
    }

    private String heroicName;
    private String attribute;
    private String Station;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeroicName() {
        return heroicName;
    }

    public void setHeroicName(String heroicName) {
        this.heroicName = heroicName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getStation() {
        return Station;
    }

    public void setStation(String station) {
        Station = station;
    }
}
