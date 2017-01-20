package com.max.novelreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.adapter.BookstoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/18.
 */

public class BookStoreFragment extends Fragment {

    public static final String TAB_RECOMMAND = "recommand";
    public static final String TAB_HOT = "hot";

    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    public BookStoreFragment() {
    }

    public static BookStoreFragment newInstance() {
        BookStoreFragment fragment = new BookStoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookstore, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initViews();
    }

    private void initViews() {
        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        tvTitleBar.setText(R.string.bookstore);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BookListFragment.newInstance(TAB_RECOMMAND));
        fragments.add(BookListFragment.newInstance(TAB_HOT));
        fragments.add(CategoryFragment.newInstance());

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.recommand));
        titles.add(getString(R.string.hot));
        titles.add(getString(R.string.category));

        BookstoreAdapter adapter = new BookstoreAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(adapter);
    }

}
