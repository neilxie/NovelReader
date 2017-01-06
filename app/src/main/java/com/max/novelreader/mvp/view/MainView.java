package com.max.novelreader.mvp.view;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface MainView {

    void initViews();

    void initFragment();

    void showPage(int index);

    void hidePage(int index);
}
