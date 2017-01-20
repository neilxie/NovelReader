package com.max.novelreader.mvp.presenter.impl;

import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.mvp.view.BookListFragmentView;
import com.max.novelreader.mvp.view.FragmentView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */

public class BookListPresenterImpl implements BookListPresenter {

    BookListFragmentView bookListFragmentView;
    String bookListType;

    public BookListPresenterImpl(String type) {
        bookListType = type;
    }

    @Override
    public void onCreate() {
        loadBookList();
    }

    @Override
    public void onRefreshList() {

    }

    @Override
    public void attach(FragmentView view) {
        bookListFragmentView = (BookListFragmentView) view;
    }

    private void loadBookList() {
        ObserverableUtil.loadRecommandNovels(new Callback<List<NovelMainBean>>() {
            @Override
            public void callback(List<NovelMainBean> novelMainBeen) {

            }
        });
    }
}
