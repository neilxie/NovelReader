package com.max.novelreader.di.modules;

import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.mvp.presenter.BookDetailPresenter;
import com.max.novelreader.mvp.presenter.impl.BookDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/2/7.
 */
@PerActivity
@Module
public class BookDetailModule {

    @Provides
    public BookDetailPresenter provideBookDetailPresenter() {
        return new BookDetailPresenterImpl();
    }
}
