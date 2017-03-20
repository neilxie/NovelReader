package com.max.novelreader.di.components;

import com.max.novelreader.di.modules.CatalogModel;
import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.ui.CatalogActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/20.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = CatalogModel.class)
public interface CatalogComponent {

    void inject(CatalogActivity activity);

}
