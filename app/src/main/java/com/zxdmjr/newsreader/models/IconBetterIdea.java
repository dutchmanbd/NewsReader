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

    public IconBetterIdea(String url, List<Icon> icons) {
        this.url = url;
        this.icons = icons;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
