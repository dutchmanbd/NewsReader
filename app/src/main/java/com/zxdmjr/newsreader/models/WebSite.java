package com.zxdmjr.newsreader.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eict on 11/20/17.
 */

public class WebSite {

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    private List<Source> sources;

    public WebSite() {
    }

    public WebSite(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
