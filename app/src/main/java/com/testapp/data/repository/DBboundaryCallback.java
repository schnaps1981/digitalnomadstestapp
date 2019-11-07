package com.testapp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.testapp.app.App;
import com.testapp.data.network.datasource.NetDataSource;

import javax.inject.Inject;

public class DBboundaryCallback {

    int page = 1;
    @Inject NetDataSource netDataSource;

    public DBboundaryCallback() { }
    public PagedList.BoundaryCallback getBoundaryCallback()
    {
        App.getComponent().inject(this);

        return new PagedList.BoundaryCallback() {
            @Override
            public void onZeroItemsLoaded() {
                super.onZeroItemsLoaded();
                Log.d("TAG", "boundaryCallback onZeroItemsLoaded");
                netDataSource.loadMoreNews(page++).observeForever(response -> {
                    if (response.isSuccessful())
                        Log.d("TAG", "DBboundaryCallback. onZeroItemsLoaded. fetch data success");
                    else
                        Log.d("TAG", "DBboundaryCallback. onZeroItemsLoaded. ERROR");
                });
            }

            @Override
            public void onItemAtFrontLoaded(@NonNull Object itemAtFront) {
                super.onItemAtFrontLoaded(itemAtFront);
                Log.d("TAG", "boundaryCallback onItemAtFrontLoaded");
            }

            @Override
            public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
                super.onItemAtEndLoaded(itemAtEnd);
                Log.d("TAG", "boundaryCallback onItemAtEndLoaded");
                netDataSource.loadMoreNews(page++).observeForever(response -> {
                    if (response.isSuccessful())
                        Log.d("TAG", "DBboundaryCallback. onItemAtEndLoaded. fetch data success");
                    else
                        Log.d("TAG", "DBboundaryCallback. onItemAtEndLoaded. ERROR");
                });
            }
        };
    }
}
