package com.max.novelreader.observer;

import com.max.novelreader.bean.AuthorBean;
import com.max.novelreader.bean.Book;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.Category;
import com.max.novelreader.bean.Chapter;
import com.max.novelreader.bean.DataBean;
import com.max.novelreader.bean.NovelBean;
import com.max.novelreader.bean.NovelLastBean;
import com.max.novelreader.bean.NovelLoadResponse;
import com.max.novelreader.bean.NovelMainBean;
import com.max.novelreader.bean.NovelMainResponse;
import com.max.novelreader.bean.NovelUrlBean;
import com.max.novelreader.bean.RecommandSameBean;
import com.max.novelreader.bean.SourceBean;
import com.max.novelreader.db.DaoManager;
import com.max.novelreader.http.HttpRequest;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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

    public static void addBookToShelf(NovelMainBean bean, final DaoManager daoManager, final Callback<Book> callback) {
        Observable.just(bean)
                .flatMap(new Func1<NovelMainBean, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(NovelMainBean bean) {
                        Book book = new Book();
                        NovelBean novelBean = bean.getNovel();
                        book.setNovelId(novelBean.getId());
                        book.setCoverUrl(novelBean.getCover());
                        book.setDescription(novelBean.getIntro());
                        book.setName(novelBean.getName());
                        book.setIsover(novelBean.isOver() ? 1 : 0);
                        book.setPostdate(Long.parseLong(novelBean.getPostdate()));
                        AuthorBean authorBean = bean.getAuthor();
                        book.setAuthor(authorBean.getName());
                        book.setAuthorUrl(authorBean.getUrl());
                        DataBean dataBean = bean.getData();
                        book.setAllvisit(Integer.parseInt(dataBean.getAllvisit()));
                        SourceBean sourceBean = bean.getSource();
                        book.setSiteHost(sourceBean.getSiteid());
                        book.setSiteHost(sourceBean.getSitehost());
                        book.setSiteKey(sourceBean.getSitekey());
                        book.setSiteName(sourceBean.getSitename());
                        book.setSiteUrl(sourceBean.getSiteurl());
                        NovelLastBean lastBean = bean.getLast();
                        book.setLastChapterId(lastBean.getId());
                        book.setLastChapterName(lastBean.getName());
                        book.setLastChapterSiteId(lastBean.getSiteid());
                        book.setLastChapterUrl(lastBean.getUrl());
                        book.setLastUpdateTime(lastBean.getTime());
                        Category category = bean.getCategory();
                        book.setCategoryId(category.getId());
                        book.setCategoryName(category.getName());
                        book.setCategoryUrl(category.getUrl());
                        NovelUrlBean urlBean = bean.getUrl();
                        book.setUrlAddMark(urlBean.getAddmark());
                        book.setUrlChapterList(urlBean.getChapterlist());
                        book.setUrlComment(urlBean.getComment());
                        book.setUrlDir(urlBean.getDir());
                        book.setUrlDown(urlBean.getDown());
                        book.setUrlFirst(urlBean.getFirst());
                        book.setUrlInfo(urlBean.getInfo());
                        book.setUrlReadEnd(urlBean.getReadend());
                        book.setUrlVote(urlBean.getVote());
                        Catalog catalog = bean.getCatalog();
                        if(catalog != null) {
                            Chapter chapter = catalog.getData().get(0);
                            book.setReadChapterId(chapter.getId());
                            book.setReadChapterName(chapter.getName());
                            book.setReadChapterUrl(chapter.getUrl());
                            book.setReadChaterSiteId(chapter.getSiteid());
                            book.setReadCount(0);
                            book.setReadPosition(0);
                        }
                        return Observable.just(book);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Book, Book>() {
                    @Override
                    public Book call(Book book) {
                        daoManager.saveBook(book);
                        return book;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Book>() {
                    @Override
                    public void call(Book book) {
                        if(callback != null) {
                            callback.callback(book);
                        }
                    }
                });
    }

    public static void deleteBookFromShelf(long id, final DaoManager daoManager, final Callback<Boolean> callback) {
        Observable.just(id)
                .map(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long id) {
                        daoManager.deleteBook(id);
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean result) {
                        if(callback != null) {
                            callback.callback(result);
                        }
                    }
                });
    }

    public static void getBook(String novelId, final DaoManager daoManager, final Callback<Book> callback) {
        Observable.just(novelId)
                .map(new Func1<String, Book>() {
                    @Override
                    public Book call(String novelId) {
                        return daoManager.getBook(novelId);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Book>() {
                    @Override
                    public void call(Book book) {
                        if(callback != null) {
                            callback.callback(book);
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

    public static void loadRecommandSame(Map<String, String> params, final Callback<RecommandSameBean> callback) {
        HttpRequest.getInstance().loadRecommandSame(params, new Action1<RecommandSameBean>() {
            @Override
            public void call(RecommandSameBean bean) {
                if(callback != null) {
                    callback.callback(bean);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if(callback != null) {
                    callback.callback(null);
                }
            }
        });
    }

    public static void loadNovelDetail(Map<String, String> params, final Callback<NovelMainBean> callback) {
        HttpRequest.getInstance().loadNovelDetail(params, new Subscriber<NovelMainResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(NovelMainResponse novelMainResponse) {
                if(callback != null) {
                    callback.callback(novelMainResponse.getMainBean());
                }
            }
        });
    }

}
