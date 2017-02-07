package com.max.novelreader.mvp.presenter;

import android.content.Context;

import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.view.BookDetailView;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface BookDetailPresenter {

    void attach(BookDetailView view);
    void setBook(NovelMainBean bean);
    void onCollapseClick();
    void onClickCatalogMore(Context context);
    void onClickCatalogLatest(Context context);
    void onClickCatalogFirst(Context context);
    void onClickCatalogSecond(Context context);
    void onClickCatalogThird(Context context);
}
