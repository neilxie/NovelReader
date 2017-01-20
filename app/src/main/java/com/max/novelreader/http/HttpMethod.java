package com.max.novelreader.http;

import com.max.novelreader.bean.NovelMainBean;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/20.
 */

public interface HttpMethod {

    @GET("api/novel/list.html")
    Observable<List<NovelMainBean>> loadRecommandNovels();


}
