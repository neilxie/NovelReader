package com.max.novelreader.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */

public class NovelLastBean implements Serializable {


    /**
     * id : 2055
     * name : 第四百四十八章 如此叙旧
     * time : 1484649147
     * siteid : 7
     * sign : null
     * oid : 2055
     * url : /novel/tianyucangqiong/read_2055.html
     */

    private String id;
    private String name;
    private String time;
    private String siteid;
    private Object sign;
    private String oid;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public Object getSign() {
        return sign;
    }

    public void setSign(Object sign) {
        this.sign = sign;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
