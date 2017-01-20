package com.max.novelreader.di.components;

import android.content.Context;

import com.max.novelreader.db.DaoManager;
import com.max.novelreader.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/19.
 */
@Singleton
@Component(modules=AppModule.class)
public interface AppComponent {

    Context getContext();
    DaoManager getDaoManager();

}
