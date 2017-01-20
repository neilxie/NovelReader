package com.max.novelreader.mvp.presenter;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.max.novelreader.bean.Book;
import com.max.novelreader.db.DaoManager;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface BookshelfPresenter extends FragmentPresenter {

    void onCreate(DaoManager daoManager);

    void onDestroy();

    void onPrepareMenu(Menu menu);

    void onMenuItemSelect(Activity activity, MenuItem item);

    void cancelEditMode(Activity activity);

    void onBookItemClick(Book book);

    boolean isBookSelected(Book book);

}
