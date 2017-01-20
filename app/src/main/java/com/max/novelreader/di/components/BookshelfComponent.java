package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.BookshelfModule;
import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.ui.fragment.BookshelfFragment;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/1/12.
 */
@PerActivity
@Subcomponent(modules = BookshelfModule.class)
public interface BookshelfComponent {

    void inject(BookshelfFragment bookshelfFragment);
}
