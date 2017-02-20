package com.max.novelreader.mvp.presenter.impl;

import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.mvp.view.BookListFragmentView;
import com.max.novelreader.mvp.view.FragmentView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/20.
 */

public class BookListPresenterImpl implements BookListPresenter {

    private static final String PAGESIZE_LIMIT = "15";
    BookListFragmentView bookListFragmentView;
    Map<String, String> params;
    int curPage = 1;
    List<NovelMainBean> bookList;
    boolean isRefreshing = false;
    boolean isLoading = false;

    public BookListPresenterImpl(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public void onCreate() {
        loadBookList(curPage);
    }

    @Override
    public void onViewCreated() {
        if(isLoading) {
            bookListFragmentView.showProgress();
        } else {
            bookListFragmentView.refreshNovelList(bookList);
        }
    }

    @Override
    public void onRefreshList() {
        isRefreshing = true;
        loadBookList(1);
    }

    @Override
    public void loadNextPage() {
        curPage++;
        loadBookList(curPage);
    }

    @Override
    public void attach(FragmentView view) {
        bookListFragmentView = (BookListFragmentView) view;
    }

    private void loadBookList(int page) {
        isLoading = true;
//        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("pagesize", PAGESIZE_LIMIT);
//        if(bookListType.equals(BookStoreFragment.TAB_HOT)) {
//            params.put("order", "monthvisit");
//        }
        ObserverableUtil.loadNovels(params, new Callback<List<NovelMainBean>>() {
            @Override
            public void callback(List<NovelMainBean> list) {
                isLoading = false;
                if(list != null && !list.isEmpty()) {
                    if(bookList == null) {
                        bookList = new ArrayList<NovelMainBean>();
                    }

                    if(isRefreshing) {
                        bookList.clear();
                        isRefreshing = false;
                        curPage = 1;
                    }

                    bookList.addAll(list);
                    bookListFragmentView.refreshNovelList(bookList);
                    bookListFragmentView.hideProgress();
                } else if(curPage != 1) {
                    curPage--;
                }
            }
        });
    }

}
