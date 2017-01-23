package com.max.novelreader.mvp.view;

import com.max.novelreader.bean.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */

public interface CategoryFragmentView extends FragmentView {

    void refreshCategories(List<Category> list);

    void showProgress();

    void hideProgress();
}
