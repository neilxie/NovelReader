package com.max.novelreader.mvp.view;

import android.view.Menu;

import com.max.novelreader.bean.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface BookshelfFragmentView {

    void showEditMode(Menu menu);
    void hideEditMode(Menu menu);
    void showProgress();
    void hideProgress();
    void refreshBookShelf(List<Book> bookList);
    void setBookShelfEmpty();
    void showMenuSelectAll();
    void showMenuUnselectAll();
    void showDelBtn();
    void hideDelBtn();
    void refreshSelectView(int selectCount);
}
