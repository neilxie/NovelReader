package com.max.novelreader.http;

import com.max.novelreader.BuildConfig;
import com.max.novelreader.bean.NovelMainBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/20.
 */

public class HttpRequest {

    private static final String BASE_URL = "http://goapi.yphsy.com/";

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

    public void loadRecommandNovels(Subscriber<List<NovelMainBean>> subscribe) {
        httpMethod.loadRecommandNovels()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

}
