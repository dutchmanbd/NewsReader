package com.zxdmjr.newsreader.retrofit;

import com.zxdmjr.newsreader.constants.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eict on 11/20/17.
 */

public class IconBetterIdeaClient {

    private static Retrofit retrofit;

    public static Retrofit getClient(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.ICON_BETTER_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
