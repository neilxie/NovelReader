package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.NovelLastBean;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.RecommandSameBean;

import java.util.List;

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
    void showSameRecommand(List<RecommandSameBean.DataBean> list);
    void showBookInShelfBtn(boolean isInShelf);
}
