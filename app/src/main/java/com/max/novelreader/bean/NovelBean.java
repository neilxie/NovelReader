package com.max.novelreader.bean;

import com.max.novelreader.http.HttpRequest;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */

public class NovelBean implements Serializable {


    /**
     * id : 7312
     * name : 天域苍穹
     * pinyin : tianyucangqiong
     * initial : t
     * caption :
     * intro : 笑尽天下英雄，宇内我为君主！万水千山，以我为尊；八荒六合，唯我称雄！我欲舞风云，凌天下，踏天域，登苍穹！谁可争锋？！诸君可愿陪我，并肩凌天下，琼霄风云舞，征战这天域苍穹？！…………天域苍穹官方群一：376497379天域苍穹官方群二：374474820
     * postdate : 1476775087
     * isgood : 1
     * status : 1
     * isover : 0
     * cover : /public/cover/05/94/95/059495318b0e2943f9257b0c12618f12.jpg
     */

    private String id;
    private String name;
    private String pinyin;
    private String initial;
    private String caption;
    private String intro;
    private String postdate;
    private String isgood;
    private String status;
    private int isover;
    private String cover;

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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getIsgood() {
        return isgood;
    }

    public void setIsgood(String isgood) {
        this.isgood = isgood;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOver() {
        return isover == 1;
    }

    public void setIsover(int isover) {
        this.isover = isover;
    }

    public String getCover() {
        return HttpRequest.BASE_URL + cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
