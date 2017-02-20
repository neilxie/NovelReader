package com.max.novelreader.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14.
 */

public class SerializableMap implements Serializable {

    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
