package com.max.novelreader.mvp.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.max.novelreader.bean.Book;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.NovelBean;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.RecommandSameBean;
import com.max.novelreader.bean.SourceBean;
import com.max.novelreader.db.DaoManager;
import com.max.novelreader.mvp.presenter.BookDetailPresenter;
import com.max.novelreader.mvp.view.BookDetailView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;
import com.max.novelreader.ui.BookDetailActivity;

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
    private DaoManager daoManager;
    private boolean isBookInShelf;
    private Book shelfBook;

    @Override
    public void attach(BookDetailView view) {
        bookDetailView = view;
    }

    @Override
    public void setBook(NovelMainBean bean) {
        novelMainBean = bean;
        bookDetailView.showNovel(bean);
        loadCatalog();
        checkNovelInShelf();
        if(bean.getAuthor() != null) {
            showIntroLess();
            loadRecommandSame();
        } else {
            loadNovelDetail();
        }
    }

    @Override
    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
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

    @Override
    public void onClickSameCategoryItem(Activity activity, RecommandSameBean.DataBean bean, View transitionView) {
        NovelMainBean mainBean = new NovelMainBean();
        NovelBean novelBean = new NovelBean();
        novelBean.setCover(bean.getNovel().getCoverOrigin());
        novelBean.setId(bean.getNovel().getId());
        novelBean.setName(bean.getNovel().getName());
        mainBean.setNovel(novelBean);
        SourceBean sourceBean = new SourceBean();
        sourceBean.setSiteid(bean.getSource().getSiteid());
        mainBean.setSource(sourceBean);
        BookDetailActivity.showBookDetail(activity, transitionView, mainBean);
    }

    @Override
    public void onClickShelf() {
        if(isBookInShelf) {
            ObserverableUtil.deleteBookFromShelf(shelfBook.getId(), daoManager);
        } else {
            ObserverableUtil.addBookToShelf(novelMainBean, daoManager, new Callback<Book>() {
                @Override
                public void callback(Book book) {
                    isBookInShelf = true;
                    shelfBook = book;
                    bookDetailView.showBookInShelfBtn(isBookInShelf);
                }
            });
        }
    }

    private void checkNovelInShelf() {
        ObserverableUtil.getBook(novelMainBean.getNovel().getId(), daoManager, new Callback<Book>() {
            @Override
            public void callback(Book book) {
                shelfBook = book;
                isBookInShelf = book != null;
                bookDetailView.showBookInShelfBtn(isBookInShelf);
            }
        });
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
                    removeSameNovel(bean);
                    recommandSameBean = bean;
                    int start = (int) (Math.random() * (bean.getData().size() - 4));
                    recommandIndex = start + 4;
                    showRecommandList(start, recommandIndex);
                }
            }
        });
    }

    private void removeSameNovel(RecommandSameBean bean) {
        List<RecommandSameBean.DataBean> list = bean.getData();
        RecommandSameBean.DataBean novel = null;
        for(RecommandSameBean.DataBean dataBean : list) {
            if(dataBean.getNovel().getId().equals(novelMainBean.getNovel().getId())) {
                novel = dataBean;
                break;
            }
        }

        list.remove(novel);
    }

    private void showRecommandList(int start, int end) {
        List<RecommandSameBean.DataBean> list = recommandSameBean.getData().subList(start, end);
        bookDetailView.showSameRecommand(list);
    }

    private void loadNovelDetail() {
        Map<String, String> params = new HashMap<>();
        params.put("novelid", novelMainBean.getNovel().getId());
        ObserverableUtil.loadNovelDetail(params, new Callback<NovelMainBean>() {
            @Override
            public void callback(NovelMainBean bean) {
                if(bean != null) {
                    novelMainBean = bean;
                    bookDetailView.showNovel(bean);
                    showIntroLess();
                    loadRecommandSame();
                }
            }
        });
    }

}
