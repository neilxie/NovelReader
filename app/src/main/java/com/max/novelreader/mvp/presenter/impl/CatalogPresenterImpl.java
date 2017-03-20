package com.max.novelreader.mvp.presenter.impl;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.mvp.presenter.CatalogPresenter;
import com.max.novelreader.mvp.view.CatalogView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */

public class CatalogPresenterImpl implements CatalogPresenter {

    CatalogView catalogView;
    String novelId;
    String siteId;
    Catalog mCatalog;

    public CatalogPresenterImpl(String novelId, String siteId) {
        this.novelId = novelId;
        this.siteId = siteId;
    }

    @Override
    public void attach(CatalogView view) {
        catalogView = view;
    }

    @Override
    public void onCreate() {
        loadCatalog();
    }

    private void loadCatalog() {
        Map<String, String> params = new HashMap<>();
        params.put("novelid", novelId);
        params.put("siteid", siteId);
        ObserverableUtil.loadCatalog(params, new Callback<Catalog>() {
            @Override
            public void callback(Catalog catalog) {
                mCatalog = catalog;
                if(catalog != null) {
                    catalogView.hideProgress();
                    catalogView.showCatalog(catalog);
                }
            }
        });
    }
}
