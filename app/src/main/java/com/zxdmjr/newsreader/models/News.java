package com.zxdmjr.newsreader.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eict on 11/21/17.
 */

public class News {


    @SerializedName("status")
    private String status;

    @SerializedName("source")
    private String source;

    @SerializedName("sortBy")
    private String sortBy;

    @SerializedName("articles")
    private List<Article> articles;

    public News(String status, String source, String sortBy, List<Article> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
