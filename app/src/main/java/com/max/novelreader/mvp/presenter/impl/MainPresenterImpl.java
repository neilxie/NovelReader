package com.max.novelreader.mvp.presenter.impl;

import com.max.novelreader.mvp.presenter.MainPresenter;
import com.max.novelreader.mvp.view.MainView;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private int currentPage = 0;

    public MainPresenterImpl() {

    }

    @Override
    public void attach(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate() {
        mainView.initViews();
        mainView.initFragment();
        mainView.showPage(currentPage);
    }

    @Override
    public void onTabSelete(int index) {
        if(index == currentPage) {
            return;
        }

        mainView.hidePage(currentPage);
        mainView.showPage(index);
        currentPage = index;
    }
}
