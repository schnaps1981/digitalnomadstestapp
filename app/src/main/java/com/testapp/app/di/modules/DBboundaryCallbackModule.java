package com.testapp.app.di.modules;

import androidx.paging.PagedList;

import com.testapp.data.repository.DBboundaryCallback;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBboundaryCallbackModule {

    @Singleton
    @Provides
    PagedList.BoundaryCallback provideDBboundaryCallback()
    {
        return new DBboundaryCallback().getBoundaryCallback();
    }
}
