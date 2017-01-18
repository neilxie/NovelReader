package com.max.novelreader.mvp.presenter;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.max.novelreader.bean.Book;
import com.max.novelreader.mvp.view.BookshelfFragmentView;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface BookshelfPresenter {

    void attach(BookshelfFragmentView view);

    void onCreate();

    void onDestroy();

    void onPrepareMenu(Menu menu);

    void onMenuItemSelect(Activity activity, MenuItem item);

    void cancelEditMode(Activity activity);

    void onBookItemClick(Book book);

}
