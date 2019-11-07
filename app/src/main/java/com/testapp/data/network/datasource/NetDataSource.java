package com.testapp.data.network.datasource;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.testapp.app.App;
import com.testapp.app.Constants;
import com.testapp.data.network.utils.NetworkState;
import com.testapp.data.network.api.NewsAPIInterface;
import com.testapp.data.database.model.NewsAPIItem;
import com.testapp.data.database.model.NewsAPIResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import java9.util.stream.StreamSupport;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java9.util.stream.Collectors.toList;

public class NetDataSource {
    private MutableLiveData response;
    private MutableLiveData networkStatus;
    private MutableLiveData liveResponseBody;
    private Completable retryCompletable;

    @Inject NewsAPIInterface api;

    public NetDataSource()
    {
        response = new MutableLiveData();
        networkStatus = new MutableLiveData();
        liveResponseBody = new MutableLiveData();
        App.getComponent().inject(this);
    }

    public LiveData<Response<NewsAPIResult>> loadMoreNews(Integer page)
    {
        if (page > Constants.MAX_PAGES)
        {
            Log.d("TAG", "Max page reached!!!");
            networkStatus.postValue(NetworkState.NO_MORE_DATA);
            return response;
        }

        networkStatus.postValue(NetworkState.LOADING);

        api.getData("android", "2019-04-00", "publishedAt", Constants.API_KEY, Long.valueOf(page))
                .enqueue(new Callback<NewsAPIResult>() {
                    @Override
                    public void onResponse(Call<NewsAPIResult> call, Response<NewsAPIResult> response) {
                        if (response.body() != null) {
                            networkStatus.postValue(NetworkState.LOADED);
                            List<NewsAPIItem> resp_list = StreamSupport.stream(response.body().getNews())
                                    //Фильтр данных, апи бывает отдает в полях null, от этого портится diffutil в адаптере
                                    .map(newsAPIItem -> {
                                        NewsAPIItem item = new NewsAPIItem();
                                        item.setUrlToImage(newsAPIItem.getUrlToImage() == null ? "NULL" : newsAPIItem.getUrlToImage());
                                        item.setUrl(newsAPIItem.getUrl() == null ? "NULL" : newsAPIItem.getUrl());
                                        item.setTitle(newsAPIItem.getTitle() == null ? "NULL" : newsAPIItem.getTitle());
                                        item.setAuthor(newsAPIItem.getAuthor() == null ? "NULL" : newsAPIItem.getAuthor());
                                        item.setContent(newsAPIItem.getContent() == null ? "NULL" : newsAPIItem.getContent());
                                        item.setDescription(newsAPIItem.getDescription() == null ? "NULL" : newsAPIItem.getDescription());
                                        item.setPublishedAt(newsAPIItem.getPublishedAt() == null ? "NULL" : newsAPIItem.getPublishedAt());
                                        return item;
                                    }).collect(toList());
                            liveResponseBody.postValue(resp_list);
                            NetDataSource.this.response.postValue(response);
                        }
                        else
                        {
                            networkStatus.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            setRetry(() -> loadMoreNews(page));
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsAPIResult> call, Throwable t) {
                        //network fail
                        networkStatus.postValue(new NetworkState(NetworkState.Status.FAILED, t.getLocalizedMessage()));
                        setRetry(() -> loadMoreNews(page));
                    }
                });
        return response;
    }

    private void setRetry(final Action action) {
        if (action == null) {
            this.retryCompletable = null;
        } else {
            this.retryCompletable = Completable.fromAction(action);
        }
    }

    @SuppressLint("CheckResult")
    public void tryAgain() {
        if (retryCompletable != null) {
            retryCompletable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                    }, throwable -> Log.d("TAG", throwable.getMessage()));
        }
    }

    public MutableLiveData<NetworkState> getNetworkState() {return networkStatus;}

    public MutableLiveData<List<NewsAPIItem>> getLiveResponseBody() { return liveResponseBody;}
}
