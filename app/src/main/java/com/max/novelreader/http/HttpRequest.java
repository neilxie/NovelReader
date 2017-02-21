package com.max.novelreader.http;

import com.max.novelreader.BuildConfig;
import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.Category;
import com.max.novelreader.bean.CategoryLoadResponse;
import com.max.novelreader.bean.NovelLoadResponse;
import com.max.novelreader.bean.RecommandSameBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/20.
 */

public class HttpRequest {

    public static final String BASE_URL = "http://goapi.yphsy.com/";

    private Retrofit retrofit;
    private HttpMethod httpMethod;

    private static class HttpRequestHolder {
        public static HttpRequest sInstance = new HttpRequest();
    }

    public static HttpRequest getInstance() {
        return HttpRequestHolder.sInstance;
    }

    private HttpRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        httpMethod = retrofit.create(HttpMethod.class);
    }

    public void loadNovels(Map<String, String> params, Action1<NovelLoadResponse> action1, Action1<Throwable> error) {
        httpMethod.loadNovels(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, error);
    }

    public void loadCategoryList(Action1<List<Category>> action1, Action1<Throwable> error) {
        httpMethod.loadCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<CategoryLoadResponse, Observable<List<Category>>>() {
                    @Override
                    public Observable<List<Category>> call(CategoryLoadResponse categoryLoadResponse) {
                        return Observable.just(categoryLoadResponse.getData());
                    }

                })
                .subscribe(action1, error);
    }

    public void loadCatalog(Map<String, String> params, Action1<Catalog> action, Action1<Throwable> error) {
        httpMethod.loadCatalog(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, error);
    }

    public void loadRecommandSame(Map<String, String> params, Action1<RecommandSameBean> action, Action1<Throwable> error) {
        httpMethod.loadRecommandSame(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, error);
    }

}
