package com.max.novelreader;

import android.app.Application;

import com.max.novelreader.di.components.AppComponent;
import com.max.novelreader.di.components.DaggerAppComponent;
import com.max.novelreader.di.modules.AppModule;

/**
 * Created by Administrator on 2017/1/19.
 */

public class App extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
