package com.max.novelreader.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */

public class Category implements Serializable {


    /**
     * id : 1
     * name : 玄幻
     * url : /sort/xuanhuan.html
     */

    private String id;
    private String name;
    private String url;
    private String key;
    private String num;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
