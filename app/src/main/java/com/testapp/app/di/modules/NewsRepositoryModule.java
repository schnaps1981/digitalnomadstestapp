package com.testapp.app.di.modules;

import com.testapp.data.repository.NewsRepository;
import com.testapp.data.repository.NewsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsRepositoryModule {
    @Singleton
    @Provides
    NewsRepository provideNewsRepository()
    {
        return new NewsRepositoryImpl();
    }
}
