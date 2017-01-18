package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.BookshelfModule;
import com.max.novelreader.ui.fragment.BookshelfFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/12.
 */
@Component(modules = BookshelfModule.class)
public interface BookshelfComponent {

    void inject(BookshelfFragment bookshelfFragment);
}
