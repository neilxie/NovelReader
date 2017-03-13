package com.max.novelreader.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.RecommandSameBean;
import com.max.novelreader.db.DaoManager;
import com.max.novelreader.mvp.view.BookDetailView;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface BookDetailPresenter {

    void attach(BookDetailView view);
    void setBook(NovelMainBean bean);
    void setDaoManager(DaoManager daoManager);
    void onCollapseClick();
    void onClickCatalogMore(Context context);
    void onClickCatalogLatest(Context context);
    void onClickCatalogFirst(Context context);
    void onClickCatalogSecond(Context context);
    void onClickCatalogThird(Context context);
    void onClickChangeNew();
    void onClickSameCategoryItem(Activity activity, RecommandSameBean.DataBean bean, View transitionView);
    void onClickShelf();
}
