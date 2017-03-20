package com.max.novelreader.mvp.presenter;

import com.max.novelreader.mvp.view.CatalogView;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface CatalogPresenter {

    void attach(CatalogView view);
    void onCreate();
}
