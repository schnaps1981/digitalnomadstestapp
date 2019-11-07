package com.testapp.ui.view;

import androidx.paging.PagedList;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.testapp.data.network.utils.NetworkState;
import com.testapp.data.database.model.NewsAPIItem;

public interface MainActivityView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void SetNetworkState(NetworkState networkState);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void SetNewsList(PagedList<NewsAPIItem> newsAPIItems);
}
