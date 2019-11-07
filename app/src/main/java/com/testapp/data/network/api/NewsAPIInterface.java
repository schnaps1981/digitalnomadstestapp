package com.testapp.data.network.api;

import com.testapp.data.database.model.NewsAPIResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {
    @GET("/v2/everything")
    Call<NewsAPIResult> getData(
            @Query("q") String q,
            @Query("from") String from,
            @Query("sortBy") String sortby,
            @Query("apiKey") String apikey,
            @Query("page") Long page
    );
}
