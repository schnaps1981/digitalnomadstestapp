package com.testapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.testapp.data.network.utils.NetworkState;
import com.testapp.data.database.model.NewsAPIItem;

public interface NewsRepository {
    void TryFetchApiData();
    MutableLiveData<NetworkState> getNetworkState();
    LiveData<PagedList<NewsAPIItem>> getNewsList();
}
