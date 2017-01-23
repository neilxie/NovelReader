package com.max.novelreader.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20.
 */

public class NovelUrlBean implements Serializable {

    /**
     * first : /novel/tianyucangqiong/read_1.html
     * info : /novel/tianyucangqiong/
     * dir : /dir/tianyucangqiong/
     * down : /novelsearch/novel/down.html?novelid=7312&novelkey=tianyucangqiong
     * readend : /novel/tianyucangqiong/readend.html
     * chapterlist : /novel/chapterlist/tianyucangqiong.html
     * addmark : /user/mark/add.html?novelid=7312
     * vote : /user/vote/add.html?novelid=7312
     * comment : /novel/tianyucangqiong/comment/1.html
     */

    private String first;
    private String info;
    private String dir;
    private String down;
    private String readend;
    private String chapterlist;
    private String addmark;
    private String vote;
    private String comment;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getReadend() {
        return readend;
    }

    public void setReadend(String readend) {
        this.readend = readend;
    }

    public String getChapterlist() {
        return chapterlist;
    }

    public void setChapterlist(String chapterlist) {
        this.chapterlist = chapterlist;
    }

    public String getAddmark() {
        return addmark;
    }

    public void setAddmark(String addmark) {
        this.addmark = addmark;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
