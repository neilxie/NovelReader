package com.max.novelreader.di.modules;

import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.mvp.presenter.impl.BookListPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/20.
 */
@PerActivity
@Module
public class BookListModule {

    String bookListType;

    public BookListModule(String type) {
        bookListType = type;
    }

    @Provides
    public BookListPresenter provideBookListPresenter() {
        return new BookListPresenterImpl(bookListType);
    }
}
