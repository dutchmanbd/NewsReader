package com.zxdmjr.newsreader.constants;

import com.zxdmjr.newsreader.interfaces.NewsService;

/**
 * Created by eict on 11/20/17.
 */

public class Constant {

    public static final String API_KEY = "f3ba7b60d3a443a89d31183e4f6b3790";

    public static final String BASE_URL = "https://newsapi.org/";
    public static final String ICON_BETTER_BASE_URL = "https://icons.better-idea.org";

    public static final String INTENT_SOURCE_ID = "source_id";
    public static final String INTENT_SOURCE_SORT_BY = "source_sort_by";
    public static final String INTENT_WEB_URL = "web_url";


    public static String getApiUrl(String source, String sortBy){
        //https://newsapi.org/v1/articles?source=the-verge&apiKey=f3ba7b60d3a443a89d31183e4f6b3790
        StringBuffer url = new StringBuffer("https://newsapi.org/v1/articles?source=");

        return url.append(source)
                .append("&apiKey=")
                .append(API_KEY).toString();
    }

}
