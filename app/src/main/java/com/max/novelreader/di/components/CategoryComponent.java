package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.CategoryModule;
import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/1/22.
 */
@PerActivity
@Component(modules = CategoryModule.class)
public interface CategoryComponent {

    void inject(CategoryFragment categoryFragment);
}
