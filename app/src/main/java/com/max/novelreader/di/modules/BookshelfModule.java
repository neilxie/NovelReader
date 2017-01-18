package com.max.novelreader.di.modules;

import com.max.novelreader.mvp.presenter.BookshelfPresenter;
import com.max.novelreader.mvp.presenter.impl.BookshelfPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/12.
 */
@Module
public class BookshelfModule {

    @Provides
    public BookshelfPresenter provideBookshelfPresenter() {
        return new BookshelfPresenterImpl();
    }
}
