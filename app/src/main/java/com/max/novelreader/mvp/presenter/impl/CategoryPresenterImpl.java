package com.max.novelreader.mvp.presenter.impl;

import com.max.novelreader.bean.Category;
import com.max.novelreader.mvp.presenter.CategoryPresenter;
import com.max.novelreader.mvp.view.CategoryFragmentView;
import com.max.novelreader.mvp.view.FragmentView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */

public class CategoryPresenterImpl implements CategoryPresenter {

    CategoryFragmentView categoryFragmentView;
    boolean isLoading;
    List<Category> categoryList;

    @Override
    public void onCreate() {
        loadCategories();
    }

    @Override
    public void onViewCreated() {
        if(isLoading) {
            categoryFragmentView.showProgress();
        } else if(categoryList != null){
            categoryFragmentView.refreshCategories(categoryList);
        }
    }

    @Override
    public void onRefreshList() {
        loadCategories();
    }

    @Override
    public void attach(FragmentView view) {
        categoryFragmentView = (CategoryFragmentView) view;
    }

    private void loadCategories() {
        ObserverableUtil.loadCategories(new Callback<List<Category>>() {
            @Override
            public void callback(List<Category> list) {
                isLoading = false;
                if(list != null && !list.isEmpty()) {
                    categoryList = list;
                    categoryFragmentView.refreshCategories(list);
                    categoryFragmentView.hideProgress();
                }
            }
        });
    }
}
