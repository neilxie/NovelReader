package com.max.novelreader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.max.novelreader.R;
import com.max.novelreader.adapter.BookstoreAdapter;
import com.max.novelreader.bean.Category;
import com.max.novelreader.bean.SerializableMap;
import com.max.novelreader.ui.fragment.BookListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/13.
 */

public class CategoryListActivity extends BaseActivity {

    public static final String EXTRA_CATEGORY = "category";
    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    Category category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);

        category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);

        initViews();
    }

    private void initViews() {
        initToolbar();
        initViewPager();
    }

    private void initToolbar() {
        tvTitleBar.setText(category.getName());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        int id = Integer.parseInt(category.getId());
        Map<String, String> hotMap = new HashMap<>();
        Map<String, String> overMap = new HashMap<>();
        hotMap.put("order", "allvisit");
        if(id != 0) {
            hotMap.put("category", category.getId());
            overMap.put("category", category.getId());
        }
        SerializableMap hotParams = new SerializableMap();
        hotParams.setMap(hotMap);
        fragments.add(BookListFragment.newInstance(hotParams));

        overMap.put("order", "allvisit");
        overMap.put("isover", "1");
        SerializableMap overParams = new SerializableMap();
        overParams.setMap(overMap);
        fragments.add(BookListFragment.newInstance(overParams));

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.hot));
        titles.add(getString(R.string.novel_over));

        BookstoreAdapter adapter = new BookstoreAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.iv_title_back)
    public void onBack(View view) {
        finish();
    }
}
