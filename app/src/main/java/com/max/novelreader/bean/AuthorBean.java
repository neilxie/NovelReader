package com.max.novelreader.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */

public class AuthorBean implements Serializable {


    /**
     * name : 风凌天下
     * url : /author/{authorpinyin}
     */

    private String name;
    private String url;

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
}
