package com.max.novelreader.event;

import com.max.novelreader.bean.Book;

/**
 * Created by Administrator on 2017/3/17.
 */

public class BookShelfUpdateEvent {

    public static final int OPRATION_ADD = 1;
    public static final int OPRATION_DEL = 2;

    private Book book;
    private int opration;

    public BookShelfUpdateEvent(Book book, int opration) {
        this.book = book;
        this.opration = opration;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getOpration() {
        return opration;
    }

    public void setOpration(int opration) {
        this.opration = opration;
    }
}
