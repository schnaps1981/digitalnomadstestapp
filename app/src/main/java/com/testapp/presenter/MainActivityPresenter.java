package com.testapp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.testapp.app.App;
import com.testapp.data.repository.NewsRepository;
import com.testapp.ui.view.MainActivityView;

import javax.inject.Inject;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    @Inject NewsRepository newsRepository;

    public void onCreate() {
        App.getComponent().inject(this);
        newsRepository.getNetworkState().observeForever(networkState -> {
            getViewState().SetNetworkState(networkState);
        });

        newsRepository.getNewsList().observeForever(newsAPIItems -> {
            getViewState().SetNewsList(newsAPIItems);
        });
    }

    public void TryArainFetchData()
    {
        newsRepository.TryFetchApiData();
    }

}
