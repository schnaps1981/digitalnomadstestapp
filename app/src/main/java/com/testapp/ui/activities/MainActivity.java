package com.testapp.ui.activities;

import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.testapp.R;
import com.testapp.data.network.utils.NetworkState;
import com.testapp.ui.adapter.NewsListAdapter;
import com.testapp.data.database.model.NewsAPIItem;
import com.testapp.presenter.MainActivityPresenter;
import com.testapp.ui.view.MainActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends MvpActivity implements MainActivityView {
    @BindView(R.id.rvMainNewsList) RecyclerView rvMainNewsList;
    private NewsListAdapter newsListAdapter;

    @InjectPresenter(type = PresenterType.GLOBAL)
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Чтобы видели котика, а так это не надо
        try {
            Thread.sleep(2000);
            setTheme(R.style.AppTheme);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        newsListAdapter = new NewsListAdapter(NewsAPIItem.DiffUtilsCallback, this::AdapterCallbackClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMainNewsList.setLayoutManager(linearLayoutManager);
        rvMainNewsList.setNestedScrollingEnabled(true);
        rvMainNewsList.setAdapter(newsListAdapter);

        mainActivityPresenter.onCreate();
    }

    @Override
    public void SetNetworkState(NetworkState networkState) {
        Log.d("TAG", "Network status recived");
        newsListAdapter.UpdateFooter(networkState);
    }

    @Override
    public void SetNewsList(PagedList<NewsAPIItem> newsAPIItems) {
        Log.d("TAG", "MainActivity. Data from db recived.");
        newsListAdapter.submitList(newsAPIItems);
    }

    private void AdapterCallbackClickListener(View view) {
        Log.d("TAG", "AdapterCallbackClickListener called");
        if (view instanceof Button) {
            Log.d("TAG", "AdapterCallbackClickListener button clicked");
            mainActivityPresenter.TryArainFetchData();
        }
        else {
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("url", view.getTag().toString());
            startActivity(intent);
        }
    }
}
