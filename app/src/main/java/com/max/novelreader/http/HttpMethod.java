package com.max.novelreader.http;

import com.max.novelreader.bean.Catalog;
import com.max.novelreader.bean.CategoryLoadResponse;
import com.max.novelreader.bean.NovelLoadResponse;
import com.max.novelreader.bean.NovelMainResponse;
import com.max.novelreader.bean.RecommandSameBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/20.
 */

public interface HttpMethod {

    @GET("api/novel/list.html")
    Observable<NovelLoadResponse> loadNovels(@QueryMap Map<String, String> params);

    @GET("api/novel/category.html")
    Observable<CategoryLoadResponse> loadCategoryList();

    @GET("api/novel/dir.html")
    Observable<Catalog> loadCatalog(@QueryMap Map<String, String> params);

    @GET("api/novel/randhot.html")
    Observable<RecommandSameBean> loadRecommandSame(@QueryMap Map<String, String> params);

    @GET("api/novel/info.html")
    Observable<NovelMainResponse> loadNovelDetail(@QueryMap Map<String, String> params);
}
