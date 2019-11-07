package com.testapp.app.di.modules;

import com.testapp.data.database.DBDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBDataSourceModule {
    @Singleton
    @Provides
    DBDataSource providesDBDataSource()
    {
        return new DBDataSource();
    }

}
