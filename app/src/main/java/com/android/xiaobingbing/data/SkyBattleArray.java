package com.android.xiaobingbing.data;

import org.litepal.crud.DataSupport;

/**
 * Created by liurenyi on 2017/5/16.
 */
public class SkyBattleArray extends DataSupport {

    private int id;
    private int floor;
    private String floorBoss;
    private String win;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getFloorBoss() {
        return floorBoss;
    }

    public void setFloorBoss(String floorBoss) {
        this.floorBoss = floorBoss;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }
}
