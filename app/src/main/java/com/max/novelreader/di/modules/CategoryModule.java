package com.max.novelreader.di.modules;

import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.mvp.presenter.CategoryPresenter;
import com.max.novelreader.mvp.presenter.impl.CategoryPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/22.
 */
@PerActivity
@Module
public class CategoryModule {

    @Provides
    public CategoryPresenter provideCategoryPresenter() {
        return new CategoryPresenterImpl();
    }
}
