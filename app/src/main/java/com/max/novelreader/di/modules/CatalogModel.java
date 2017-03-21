package com.max.novelreader.di.modules;

import com.max.novelreader.di.scopes.PerActivity;
import com.max.novelreader.mvp.presenter.CatalogPresenter;
import com.max.novelreader.mvp.presenter.impl.CatalogPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/20.
 */
@PerActivity
@Module
public class CatalogModel {

    String novelId;
    String siteId;
    int curChapterOId;

    public CatalogModel(String novelId, String siteId, int curChapter) {
        this.novelId = novelId;
        this.siteId = siteId;
        curChapterOId = curChapter;
    }

    @Provides
    public CatalogPresenter provideCatalogPresenter() {
        return new CatalogPresenterImpl(novelId, siteId, curChapterOId);
    }
}
