package com.zxdmjr.newsreader.interfaces;

import com.zxdmjr.newsreader.models.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by eict on 11/20/17.
 */

public interface IconBetterIdeaService {

    @GET("")
    Call<IconBetterIdea> getIconUrl(@Url String url);
}
