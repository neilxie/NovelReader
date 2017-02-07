package com.max.novelreader.mvp.presenter.impl;

import android.content.Context;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.presenter.BookDetailPresenter;
import com.max.novelreader.mvp.view.BookDetailView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/7.
 */

public class BookDetailPresenterImpl implements BookDetailPresenter {

    private BookDetailView bookDetailView;
    private NovelMainBean novelMainBean;
    private boolean isIntroShowAll = false;

    @Override
    public void attach(BookDetailView view) {
        bookDetailView = view;
    }

    @Override
    public void setBook(NovelMainBean bean) {
        novelMainBean = bean;
        bookDetailView.showNovel(bean);
        showIntroLess();
        loadCatalog();
    }

    @Override
    public void onCollapseClick() {
        isIntroShowAll = !isIntroShowAll;
        if(isIntroShowAll) {
            bookDetailView.showIntroAll(novelMainBean.getNovel().getIntro());
        } else {
            showIntroLess();
        }
    }

    @Override
    public void onClickCatalogMore(Context context) {

    }

    @Override
    public void onClickCatalogLatest(Context context) {

    }

    @Override
    public void onClickCatalogFirst(Context context) {

    }

    @Override
    public void onClickCatalogSecond(Context context) {

    }

    @Override
    public void onClickCatalogThird(Context context) {

    }

    private void showIntroLess() {
        String intro = novelMainBean.getNovel().getIntro();
        if(intro.length() > 60) {
            intro = intro.substring(0, 60) + "...";
        }
        bookDetailView.showIntroLess(intro);
    }

    private void loadCatalog() {
        bookDetailView.showCatalogProgress();
        Map<String, String> params = new HashMap<>();
        params.put("novelid", novelMainBean.getNovel().getId());
        params.put("siteid", novelMainBean.getSource().getSiteid());
        ObserverableUtil.loadCatalog(params, new Callback<Catalog>() {
            @Override
            public void callback(Catalog catalog) {
                if(catalog != null) {
                    bookDetailView.hideCatalogProgress();
                    bookDetailView.showCatalog(novelMainBean.getLast(), catalog);
                }
            }
        });
    }

}
