package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.Catalog;

/**
 * Created by Administrator on 2017/3/20.
 */

public interface CatalogView {

    void hideProgress();
    void showCatalog(Catalog catalog);
}
