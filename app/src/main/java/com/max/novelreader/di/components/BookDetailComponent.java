package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.BookDetailModule;
import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.ui.BookDetailActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/2/7.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = BookDetailModule.class)
public interface BookDetailComponent {

    void inject(BookDetailActivity activity);
}
