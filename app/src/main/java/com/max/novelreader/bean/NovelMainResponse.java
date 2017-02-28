package com.max.novelreader.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/2/27.
 */

public class NovelMainResponse {


    /**
     * status : 1
     * info : success
     * data : {"novel":{"id":"7312","name":"天域苍穹","pinyin":"tianyucangqiong","initial":"t","caption":"","intro":"笑尽天下英雄，宇内我为君主！万水千山，以我为尊；八荒六合，唯我称雄！我欲舞风云，凌天下，踏天域，登苍穹！谁可争锋？！诸君可愿陪我，并肩凌天下，琼霄风云舞，征战这天域苍穹？！\u2026\u2026\u2026\u2026天域苍穹官方群一：376497379天域苍穹官方群二：374474820","postdate":"1476775087","isgood":"1","status":"1","isover":"0","cover":"/public/cover/05/94/95/059495318b0e2943f9257b0c12618f12.jpg"},"author":{"name":"风凌天下","url":"/author/{authorpinyin}"},"category":{"id":"1","name":"玄幻","url":"/sort/xuanhuan.html"},"last":{"id":"2117","name":"第五百零九章 战而胜之","time":"1488195166","siteid":"7","sign":null,"oid":"2117","url":"/novel/tianyucangqiong/read_2117.html"},"data":{"allvisit":"2265283","monthvisit":"0","weekvisit":"0","dayvisit":"0","marknum":"0","votenum":"0","downnum":"0","star":"0","starnum":"0","starval":"0"},"source":{"siteid":"7","sitename":"推荐源","siteurl":"http://www.biquge.tw","sitekey":"biqugetw","orderid":"2117","sitehost":"biquge.tw"},"url":{"first":"/novel/tianyucangqiong/read_1.html","info":"/novel/tianyucangqiong/","dir":"/dir/tianyucangqiong/","down":"/novelsearch/novel/down.html?novelid=7312&novelkey=tianyucangqiong","readend":"/novel/tianyucangqiong/readend.html","chapterlist":"/novel/chapterlist/tianyucangqiong.html","addmark":"/user/mark/add.html?novelid=7312","vote":"/user/vote/add.html?novelid=7312","comment":"/novel/tianyucangqiong/comment/1.html"}}
     */

    private int status;
    private String info;
    @SerializedName("data")
    private NovelMainBean mainBean;

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

    public NovelMainBean getMainBean() {
        return mainBean;
    }

    public void setMainBean(NovelMainBean mainBean) {
        this.mainBean = mainBean;
    }

}
