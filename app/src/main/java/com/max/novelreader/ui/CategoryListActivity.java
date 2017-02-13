package com.max.novelreader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.max.novelreader.R;

/**
 * Created by Administrator on 2017/2/13.
 */

public class CategoryListActivity extends BaseActivity {

    public static final String EXTRA_CATEGORY = "category";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
    }
}
