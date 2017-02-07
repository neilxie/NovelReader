package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.NovelLastBean;
import com.max.novelreader.bean.NovelMainBean;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface BookDetailView {

    void showNovel(NovelMainBean bean);
    void showIntroAll(String intro);
    void showIntroLess(String intro);
    void showCatalog(NovelLastBean bean, Catalog catalog);
    void showCatalogProgress();
    void hideCatalogProgress();
}
