package com.max.novelreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.novelreader.R;
import com.max.novelreader.adapter.CategoryAdapter;
import com.max.novelreader.bean.Category;
import com.max.novelreader.di.components.CategoryComponent;
import com.max.novelreader.di.components.DaggerCategoryComponent;
import com.max.novelreader.di.modules.CategoryModule;
import com.max.novelreader.mvp.presenter.CategoryPresenter;
import com.max.novelreader.mvp.view.CategoryFragmentView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CategoryFragment extends Fragment implements CategoryFragmentView {

    CategoryComponent component;
    @Inject
    CategoryPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    LinearLayoutManager layoutManager;
    CategoryAdapter adapter;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerCategoryComponent.builder().categoryModule(new CategoryModule()).build();
        component.inject(this);

        presenter.attach(this);
        presenter.onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initViews();
        presenter.onViewCreated();
    }

    private void initViews() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        swipelayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshList();
            }
        });
        adapter = new CategoryAdapter(getContext(), new ArrayList<Category>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshCategories(List<Category> list) {
        adapter.setCategoryList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        swipelayout.post(new Runnable() {
            @Override
            public void run() {
                swipelayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        swipelayout.setRefreshing(false);
    }
}
