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
import com.max.novelreader.adapter.BookListAdapter;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.SerializableMap;
import com.max.novelreader.di.components.BookListComponent;
import com.max.novelreader.di.components.DaggerBookListComponent;
import com.max.novelreader.di.modules.BookListModule;
import com.max.novelreader.mvp.presenter.BookListPresenter;
import com.max.novelreader.mvp.view.BookListFragmentView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    BookListAdapter adapter;
    int lastVisibleItem;
    LinearLayoutManager layoutManager;
    Map<String, String> params;

    public BookListFragment() {
    }

    public static BookListFragment newInstance(SerializableMap params) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM, params);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SerializableMap serializableMap = (SerializableMap) getArguments().getSerializable(PARAM);
        params = serializableMap.getMap();
        component = DaggerBookListComponent.builder().bookListModule(new BookListModule(params)).build();
        component.inject(this);

        presenter.attach(this);

        presenter.onCreate();
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
        presenter.onViewCreated();
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        swipelayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));
        swipelayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshList();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    presenter.loadNextPage();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
        adapter = new BookListAdapter(getContext(), presenter, new ArrayList<NovelMainBean>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refreshNovelList(List<NovelMainBean> list) {
        adapter.setNovelList(list);
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
