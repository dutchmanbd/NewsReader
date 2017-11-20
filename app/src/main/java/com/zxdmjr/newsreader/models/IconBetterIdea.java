package com.zxdmjr.newsreader.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eict on 11/20/17.
 */

public class IconBetterIdea {

    @SerializedName("url")
    private String url;

    @SerializedName("icons")
    private List<Icon> icons;


}
