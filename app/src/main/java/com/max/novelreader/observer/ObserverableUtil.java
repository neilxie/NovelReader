package com.max.novelreader.observer;

import com.max.novelreader.bean.Book;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.Chapter;
import com.max.novelreader.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/13.
 */

public class ObserverableUtil {

    public static void loadBookShelf(final Callback<List<Book>> callback) {
        Observable.create(new Observable.OnSubscribe<List<Book>>() {
            @Override
            public void call(Subscriber<? super List<Book>> subscriber) {
                List<Book> list = new ArrayList<Book>();
                for(int i = 0; i < 10; i++) {
                    list.add(createBook());
                }


//                Book book1 = new Book();
//                book1.title = "太古神王";
//                book1.author = "净无痕";
//                book1.bookUrl = "http://www.biquge5200.com/2_2157/";
//                book1.description = "九天大，天穹之上有九条星河，亿万星辰，皆为武命星辰，武道之人，可沟通星辰，觉醒星魂，成武命修士。传说，九天大最为厉害的武修，每突破一个境界，便能开辟一扇星门，从而沟通一颗星辰，直至，让九重天上，都有自己的武命星辰，化身通天彻地的太古神王。亿万生灵、诸天万界，秦问天笑看苍天，他要做天空，最亮的那颗星辰。各位书友要是觉得《太古神王》还不错的话请不要忘记向您群和微博里的朋友推荐哦！";
//                book1.id = Utils.stringToMD5(book.title + File.separator + book.author);
//                book1.imgUrl = "http://www.biquge5200.com/files/article/image/2/2157/2157s.jpg";
//                book1.category = "玄幻小说";
//                Catalog catalog1 = new Catalog();
//                catalog.url = "http://www.biquge5200.com/2_2157/";
//                List<Chapter> chapters1 = new ArrayList<Chapter>();
//                Chapter chapter1 = new Chapter();
//                chapter1.title = "第一章 断脉修行";
//                chapter1.url = "http://www.biquge5200.com/2_2157/1549284.html";
//                chapters1.add(chapter1);
//                catalog1.chapterList = chapters1;
//                book1.catalog = catalog1;
//                list.add(book1);

                subscriber.onNext(list);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Book>>() {
                    @Override
                    public void call(List<Book> books) {
                        if(callback != null) {
                            callback.callback(books);
                        }
                    }
                });
    }

    private static Book createBook() {
        Book book = new Book();
        book.title = "圣墟";
        book.author = "辰东";
        book.bookUrl = "http://www.biquge5200.com/52_52542/";
        book.description = "在破败中崛起，在寂灭中复苏。<br>沧海成尘，雷电枯竭，那一缕幽雾又一次临近大地，世间的枷锁被打开了，一个全新的世界就此揭开神秘的一角……";
        book.id = Utils.stringToMD5(book.title + File.separator + book.author);
        book.imgUrl = "http://qidian.qpic.cn/qdbimg/349573/1004608738/180";
        book.category = "玄幻小说";
        Catalog catalog = new Catalog();
        catalog.url = "http://www.biquge5200.com/52_52542/";
        List<Chapter> chapters = new ArrayList<Chapter>();
        Chapter chapter = new Chapter();
        chapter.title = "第一章 沙漠中的彼岸花";
        chapter.url = "http://www.biquge5200.com/52_52542/20380548.html";
        chapters.add(chapter);
        catalog.chapterList = chapters;
        book.catalog = catalog;

        return book;
    }
}
