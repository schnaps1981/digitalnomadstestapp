package com.testapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.testapp.app.App;
import com.testapp.app.Constants;
import com.testapp.data.database.DBDataSource;
import com.testapp.data.database.NewsDBDao;
import com.testapp.data.network.datasource.NetDataSource;
import com.testapp.data.database.model.NewsAPIItem;
import com.testapp.data.network.utils.NetworkState;
import com.testapp.data.repository.converters.SecsToMills;

import java.util.List;

import javax.inject.Inject;

public class NewsRepositoryImpl implements NewsRepository{

    private LiveData<List<NewsAPIItem>> liveResponseBody;

    @Inject NewsDBDao newsDBDao;
    @Inject DBDataSource dbDataSource;
    @Inject NetDataSource netDataSource;

    public NewsRepositoryImpl()
    {
        App.getComponent().inject(this);

        new Thread(() -> {
            Long cacheExpired = System.currentTimeMillis() - SecsToMills.secsToMills(Constants.DB_CHACHE_LIFETIME_SEC);
            newsDBDao.deleteOldNews(cacheExpired);
        }).start();

        liveResponseBody = netDataSource.getLiveResponseBody();

        liveResponseBody.observeForever(newsAPIItems -> {
            //доступ к базе должен быть не из UI поток
            new Thread(() -> newsDBDao.insert(newsAPIItems)).start();
        });
    }

    @Override
    public void TryFetchApiData() {
        netDataSource.tryAgain();
    }

    @Override
    public MutableLiveData<NetworkState> getNetworkState() {
        return netDataSource.getNetworkState();
    }

    @Override
    public LiveData<PagedList<NewsAPIItem>> getNewsList() {
        return dbDataSource.pagedListLiveData;
    }
}
