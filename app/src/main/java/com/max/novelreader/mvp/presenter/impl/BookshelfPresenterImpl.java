package com.max.novelreader.mvp.presenter.impl;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.max.novelreader.R;
import com.max.novelreader.mvp.presenter.BookshelfPresenter;
import com.max.novelreader.mvp.view.BookshelfFragmentView;

/**
 * Created by Administrator on 2017/1/5.
 */

public class BookshelfPresenterImpl implements BookshelfPresenter {

    private BookshelfFragmentView bookshelfFragmentView;
    private boolean isMenuEditMode = false;
    private boolean isSelectAll = false;

    @Override
    public void attach(BookshelfFragmentView view) {
        bookshelfFragmentView = view;
    }

    @Override
    public void onPrepareMenu(Menu menu) {
        if(isMenuEditMode) {
            bookshelfFragmentView.showEditMode(menu);
        } else {
            bookshelfFragmentView.hideEditMode(menu);
        }
    }

    @Override
    public void onMenuItemSelect(Activity activity, MenuItem item) {
        if(item.getItemId() == R.id.menu_edit) {
            isMenuEditMode = true;
            activity.invalidateOptionsMenu();
        } else if(isSelectAll){
            isSelectAll = false;
            item.setTitle(R.string.menu_select_all);
        } else {
            isSelectAll = true;
            item.setTitle(R.string.menu_cancel_all);
        }
    }

    @Override
    public void cancelEditMode(Activity activity) {
        isMenuEditMode = false;
        activity.invalidateOptionsMenu();
    }
}
