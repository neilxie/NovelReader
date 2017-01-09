package com.max.novelreader.di.modules;

import com.max.novelreader.mvp.presenter.MainPresenter;
import com.max.novelreader.mvp.presenter.impl.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/9.
 */
@Module
public class MainModule {

    @Provides
    MainPresenter provideMainPresenter() {
        return new MainPresenterImpl();
    }
}
