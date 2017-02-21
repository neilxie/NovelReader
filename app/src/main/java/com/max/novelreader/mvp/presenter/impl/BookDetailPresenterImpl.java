package com.max.novelreader.mvp.presenter.impl;

import android.content.Context;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.RecommandSameBean;
import com.max.novelreader.mvp.presenter.BookDetailPresenter;
import com.max.novelreader.mvp.view.BookDetailView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/7.
 */

public class BookDetailPresenterImpl implements BookDetailPresenter {

    private BookDetailView bookDetailView;
    private NovelMainBean novelMainBean;
    private boolean isIntroShowAll = false;
    private RecommandSameBean recommandSameBean;
    private int recommandIndex = 0;

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
        loadRecommandSame();
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

    @Override
    public void onClickChangeNew() {
        if(recommandSameBean == null) {
            return;
        }

        int start = recommandIndex;
        recommandIndex += 4;
        recommandIndex = recommandIndex >= recommandSameBean.getData().size() ? 4 : recommandIndex;
        start = start >= recommandIndex ? 0 : start;
        showRecommandList(start, recommandIndex);
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

    private void loadRecommandSame() {
        Map<String, String> params = new HashMap<>();
        params.put("categoryid", novelMainBean.getCategory().getId());
        params.put("randnum", "50");
        params.put("num", "6");
        ObserverableUtil.loadRecommandSame(params, new Callback<RecommandSameBean>() {
            @Override
            public void callback(RecommandSameBean bean) {
                if(bean != null) {
                    recommandSameBean = bean;
                    recommandIndex += 4;
                    showRecommandList(0, recommandIndex);
                }
            }
        });
    }

    private void showRecommandList(int start, int end) {
        List<RecommandSameBean.DataBean> list = recommandSameBean.getData().subList(start, end);
        bookDetailView.showSameRecommand(list);
    }

}
