package com.max.novelreader.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.di.components.CatalogComponent;
import com.max.novelreader.di.components.DaggerCatalogComponent;
import com.max.novelreader.di.modules.CatalogModel;
import com.max.novelreader.mvp.presenter.CatalogPresenter;
import com.max.novelreader.mvp.view.CatalogView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/20.
 */

public class CatalogActivity extends BaseActivity implements CatalogView {

    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_NOVEL_ID = "novelId";
    private static final String EXRRA_SITE_ID = "siteId";

    CatalogComponent component;
    String title;
    @Inject
    CatalogPresenter presenter;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static void showCatalogList(Context context, String novelId, String siteId, String title) {
        Intent intent = new Intent(context, CatalogActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_NOVEL_ID, novelId);
        intent.putExtra(EXRRA_SITE_ID, siteId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Intent intent = getIntent();
        title = intent.getStringExtra(EXTRA_TITLE);
        String novelId = intent.getStringExtra(EXTRA_NOVEL_ID);
        String siteId = intent.getStringExtra(EXRRA_SITE_ID);

        initViews();

        component = DaggerCatalogComponent.builder().appComponent(getAppComponent()).catalogModel(new CatalogModel(novelId, siteId)).build();
        component.inject(this);

        presenter.onCreate();
    }

    private void initViews() {
        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        tvTitle.setText(title);
        toolbar.setTitle("");
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @OnClick(R.id.iv_title_back)
    public void onClickBack(View view) {
        finish();
    }



    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showCatalog(Catalog catalog) {

    }
}
