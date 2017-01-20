package com.max.novelreader.di.modules;

import android.content.Context;

import com.max.novelreader.db.DaoManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/1/19.
 */
@Module
public class AppModule {

    private Context mContext;
    private DaoManager daoManager;

    public AppModule(Context context) {
        mContext = context;
        daoManager = new DaoManager(context);
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public DaoManager provideDaoManager() {
        return daoManager;
    }
}
