package com.testapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.testapp.app.App;
import com.testapp.app.Constants;
import com.testapp.data.database.model.NewsAPIItem;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;


public class DBDataSource {

    @Inject
    NewsDBDao newsDBDao;
    @Inject PagedList.BoundaryCallback bCallback;

    public LiveData<PagedList<NewsAPIItem>> pagedListLiveData;
    private DataSource.Factory<Integer, NewsAPIItem> datasourceFactory;

    public DBDataSource(){
        App.getComponent().inject(this);

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .setPrefetchDistance(Constants.PREFETCH_SIZE)
                .build();
        Executor executor = Executors.newFixedThreadPool(Constants.THREADS);

        datasourceFactory = newsDBDao.getNewsByPublish();


        pagedListLiveData = (new LivePagedListBuilder(datasourceFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .setBoundaryCallback(bCallback)
                .build();
    }
}
