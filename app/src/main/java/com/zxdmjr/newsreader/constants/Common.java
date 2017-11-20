package com.zxdmjr.newsreader.constants;

import com.zxdmjr.newsreader.interfaces.IconBetterIdeaService;
import com.zxdmjr.newsreader.interfaces.NewsService;
import com.zxdmjr.newsreader.retrofit.IconBetterIdeaClient;
import com.zxdmjr.newsreader.retrofit.RetrofitApiClient;

/**
 * Created by eict on 11/20/17.
 */

public class Common {

    public static NewsService getNewsService(){

        return RetrofitApiClient.getClient().create(NewsService.class);
    }

    public static IconBetterIdeaService getIconBetterIdeaService(){

        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }
}
