package com.max.novelreader.observer;

import com.max.novelreader.bean.Book;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.Category;
import com.max.novelreader.bean.NovelLoadResponse;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.db.DaoManager;
import com.max.novelreader.http.HttpRequest;

import java.util.List;
import java.util.Map;

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

    public static void loadNovels(Map<String, String> params, final Callback<List<NovelMainBean>> callback) {
        HttpRequest.getInstance().loadNovels(params, new Action1<NovelLoadResponse>() {
            @Override
            public void call(NovelLoadResponse response) {
                if (callback != null) {
                    callback.callback(response.getData());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (callback != null) {
                    callback.callback(null);
                }
            }
        });
    }

    public static void loadCategories(final Callback<List<Category>> callback) {
        HttpRequest.getInstance().loadCategoryList(new Action1<List<Category>>() {
            @Override
            public void call(List<Category> list) {
                if(callback != null) {
                    callback.callback(list);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (callback != null) {
                    callback.callback(null);
                }
            }
        });
    }

    public static void loadCatalog(Map<String, String> params, final Callback<Catalog> callback) {
        HttpRequest.getInstance().loadCatalog(params, new Action1<Catalog>() {
            @Override
            public void call(Catalog catalog) {
                if(callback != null) {
                    callback.callback(catalog);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (callback != null) {
                    callback.callback(null);
                }
            }
        });
    }

}
