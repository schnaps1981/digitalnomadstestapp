package com.testapp.data.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsAPIResult {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private long totalResults;

    @SerializedName("articles")
    @Expose
    private List<NewsAPIItem> news;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public long getTotalResults() { return totalResults; }
    public void setTotalResults(long totalResults) { this.totalResults = totalResults;}

    public List<NewsAPIItem> getNews() {return news; }
    public void setNews(List<NewsAPIItem> news) { this.news = news;}
}
