package com.max.novelreader.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */

public class CategoryLoadResponse {

    /**
     * status : 1
     * info : success
     * data : [{"id":"0","key":"all","name":"全部","num":128761},{"id":"1","name":"玄幻","key":"xuanhuan","num":33948},{"id":"3","name":"武侠","key":"wuxia","num":11654},{"id":"5","name":"都市","key":"dushi","num":20468},{"id":"6","name":"历史","key":"lishi","num":12444},{"id":"8","name":"游戏","key":"youxi","num":4265},{"id":"10","name":"科幻","key":"kehuan","num":12584},{"id":"13","name":"女生","key":"nvsheng","num":28212}]
     */

    private int status;
    private String info;
    private List<Category> data;

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

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

}
