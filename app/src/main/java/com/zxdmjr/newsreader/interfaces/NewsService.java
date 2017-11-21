package com.zxdmjr.newsreader.interfaces;

import com.zxdmjr.newsreader.models.News;
import com.zxdmjr.newsreader.models.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by eict on 11/20/17.
 */

public interface NewsService {

    @GET("v1/sources?language=en")
    Call<WebSite> getSources();

    @GET
    Call<News> getNewsArticles(@Url String url);
}
