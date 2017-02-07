package com.max.novelreader.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/13.
 */

public class Catalog {

    /**
     * status : 1
     * info : success
     * data : [{"id":"60265","name":"荣耀,你我同在!\u2014\u2014给我的兄弟","siteid":"7","url":"http://www.biquge.tw/bookdata/8/7423/3531785.txt","time":"1480013072","oid":1}]
     */

    private int status;
    private String info;
    private List<Chapter> data;
    private int total;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Chapter> getData() {
        return data;
    }

    public void setData(List<Chapter> data) {
        this.data = data;
    }

    public int getTotal() {
        return data != null && !data.isEmpty() ? data.size() : 0;
    }
}
