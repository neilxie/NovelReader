package com.max.novelreader.mvp.presenter;

/**
 * Created by Administrator on 2017/1/20.
 */

public interface BookListPresenter extends FragmentPresenter {

    void onCreate();
    void onViewCreated();
    void onRefreshList();
    void loadNextPage();
}
