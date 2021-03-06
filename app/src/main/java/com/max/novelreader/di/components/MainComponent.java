package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.MainModule;
import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.ui.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/9.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);

    BookshelfComponent bookshelfComponent();
}
