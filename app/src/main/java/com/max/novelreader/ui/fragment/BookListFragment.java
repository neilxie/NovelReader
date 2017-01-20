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
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.di.components.BookListComponent;
import com.max.novelreader.di.components.DaggerBookListComponent;
import com.max.novelreader.di.modules.BookListModule;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.mvp.view.BookListFragmentView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/18.
 */

public class BookListFragment extends Fragment implements BookListFragmentView {

    private static final String PARAM = "param";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;
    @Inject
    BookListPresenter presenter;

    BookListComponent component;
    String type;

    public BookListFragment() {
    }

    public static BookListFragment newInstance(String param) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putString(PARAM, param);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString(PARAM);
        component = DaggerBookListComponent.builder().bookListModule(new BookListModule(type)).build();
        component.inject(this);

        presenter.attach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booklist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        presenter.onCreate();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    @Override
    public void refreshNovelList(List<NovelMainBean> list) {

    }
}
