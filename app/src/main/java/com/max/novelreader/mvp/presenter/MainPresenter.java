package com.max.novelreader.mvp.presenter;

import com.max.novelreader.mvp.view.MainView;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface MainPresenter {

    void attach(MainView mainView);

    void onCreate();

    void onTabSelete(int index);

}
