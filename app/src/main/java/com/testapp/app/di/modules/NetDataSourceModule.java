package com.testapp.app.di.modules;

import com.testapp.data.network.datasource.NetDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetDataSourceModule {
    @Singleton
    @Provides
    NetDataSource provideNetDataSource()
    {
        return new NetDataSource();
    }
}
