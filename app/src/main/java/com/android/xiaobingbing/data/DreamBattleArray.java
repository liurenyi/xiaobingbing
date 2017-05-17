package com.android.xiaobingbing.data;

import org.litepal.crud.DataSupport;

/**
 * Created by liurenyi on 2017/5/13.
 */
public class DreamBattleArray extends DataSupport {

    private int id;
    private String bossId;
    private String bossName;
    private String bossRaider;
    private String bossVersion;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBossRaider() {
        return bossRaider;
    }

    public void setBossRaider(String bossRaider) {
        this.bossRaider = bossRaider;
    }

    public String getBossVersion() {
        return bossVersion;
    }

    public void setBossVersion(String bossVersion) {
        this.bossVersion = bossVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
