package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.BookListModule;
import com.max.novelreader.ui.fragment.BookListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/20.
 */
@Component(modules = BookListModule.class)
public interface BookListComponent {

    void inject(BookListFragment fragment);
}
