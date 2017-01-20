package com.max.novelreader.mvp.presenter.impl;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.max.novelreader.R;
import com.max.novelreader.bean.Book;
import com.max.novelreader.db.DaoManager;
import com.max.novelreader.event.DelBsEvent;
import com.max.novelreader.mvp.presenter.BookshelfPresenter;
import com.max.novelreader.mvp.view.BookshelfFragmentView;
import com.max.novelreader.mvp.view.FragmentView;
import com.max.novelreader.observer.Callback;
import com.max.novelreader.observer.ObserverableUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class BookshelfPresenterImpl implements BookshelfPresenter {

    private BookshelfFragmentView bookshelfFragmentView;
    private boolean isMenuEditMode = false;
    private boolean isSelectAll = false;
    private List<Book> selectedBooks;
    private List<Book> bookList;
    DaoManager daoManager;

    @Override
    public void attach(FragmentView view) {
        bookshelfFragmentView = (BookshelfFragmentView) view;
    }

    @Override
    public void onCreate(DaoManager daoManager) {
        this.daoManager = daoManager;
        loadBookShelf();

        EventBus.getDefault().register(this);

        assert daoManager == null : "DaoManager object is null";
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPrepareMenu(Menu menu) {
        if(isMenuEditMode) {
            bookshelfFragmentView.showEditMode(menu);
        } else {
            bookshelfFragmentView.hideEditMode(menu);
            unselectAllBooks();
        }
    }

    @Override
    public void onMenuItemSelect(Activity activity, MenuItem item) {
        if(item.getItemId() == R.id.menu_edit) {
            isMenuEditMode = true;
//            activity.invalidateOptionsMenu();
            bookshelfFragmentView.showDelBtn();
            bookshelfFragmentView.showEditMode(null);
        } else if(isSelectAll){
            isSelectAll = false;
            unselectAllBooks();
            bookshelfFragmentView.showMenuSelectAll();
        } else {
            isSelectAll = true;
            selectAllBooks();
            bookshelfFragmentView.showMenuUnselectAll();
        }
    }

    @Override
    public void cancelEditMode(Activity activity) {
        isMenuEditMode = false;
//        activity.invalidateOptionsMenu();
        bookshelfFragmentView.hideDelBtn();
        bookshelfFragmentView.hideEditMode(null);
        unselectAllBooks();
        isSelectAll = false;
        bookshelfFragmentView.showMenuSelectAll();
    }

    @Override
    public void onBookItemClick(Book book) {
        if(selectedBooks == null) {
            selectedBooks = new ArrayList<>();
        }

        if(!selectedBooks.contains(book)) {
            selectedBooks.add(book);

            int bookCount = bookList != null ? bookList.size() : 0;
            if(bookCount == selectedBooks.size()) {
                isSelectAll = true;
                bookshelfFragmentView.showMenuUnselectAll();
            }
        } else {
            selectedBooks.remove(book);
            if(isSelectAll){
                isSelectAll = false;
                bookshelfFragmentView.showMenuSelectAll();
            }
        }

        bookshelfFragmentView.refreshSelectView(selectedBooks.size());
    }

    @Override
    public boolean isBookSelected(Book book) {
        return selectedBooks != null && selectedBooks.contains(book);
    }

    private void selectAllBooks() {
        if(bookList != null) {
            if(selectedBooks == null) {
                selectedBooks = new ArrayList<>();
            }

            selectedBooks.clear();
            for(Book book : bookList) {
                selectedBooks.add(book);
            }

            bookshelfFragmentView.refreshSelectView(selectedBooks.size());
        }
    }

    private void unselectAllBooks() {
        if(selectedBooks != null) {
            selectedBooks.clear();
            bookshelfFragmentView.refreshSelectView(selectedBooks.size());
        }
    }

    private void loadBookShelf() {
        bookshelfFragmentView.showProgress();
        ObserverableUtil.loadBookShelf(daoManager, new Callback<List<Book>>() {
            @Override
            public void callback(List<Book> books) {
                bookshelfFragmentView.hideProgress();
                if(books != null && !books.isEmpty()) {
                    bookshelfFragmentView.refreshBookShelf(books);
                } else {
                    bookshelfFragmentView.showBookShelfEmpty();
                }

                bookList = books;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteSelectedBookEvent(DelBsEvent event) {
        if(bookList != null && !bookList.isEmpty()
                && selectedBooks != null && !selectedBooks.isEmpty()) {
            bookList.removeAll(selectedBooks);
            selectedBooks.clear();
            bookshelfFragmentView.refreshSelectView(0);
        }
    }
}
