package com.max.novelreader.observer;

import com.max.novelreader.bean.Book;
import com.max.novelreader.db.DaoManager;

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

    public static void loadBookShelf(final DaoManager daoManager, final Callback<List<Book>> callback) {
        Observable.create(new Observable.OnSubscribe<List<Book>>() {
            @Override
            public void call(Subscriber<? super List<Book>> subscriber) {
//                List<Book> list = new ArrayList<Book>();
//                for(int i = 0; i < 10; i++) {
//                    list.add(createBook());
//                }

                subscriber.onNext(daoManager.listBookshelf());
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

}
