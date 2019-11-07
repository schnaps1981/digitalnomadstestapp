package com.testapp.app.di.component;

import com.testapp.data.database.DBDataSource;
import com.testapp.data.network.datasource.NetDataSource;
import com.testapp.app.di.modules.AppContextModule;
import com.testapp.app.di.modules.DBDataSourceModule;
import com.testapp.app.di.modules.DBboundaryCallbackModule;
import com.testapp.app.di.modules.NetDataSourceModule;
import com.testapp.app.di.modules.NewsAPClientModule;
import com.testapp.app.di.modules.NewsRepositoryModule;
import com.testapp.app.di.modules.OkHttpClientModule;
import com.testapp.app.di.modules.RoomModule;
import com.testapp.presenter.MainActivityPresenter;
import com.testapp.data.repository.DBboundaryCallback;
import com.testapp.data.repository.NewsRepositoryImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppContextModule.class,
        DBDataSourceModule.class,
        NetDataSourceModule.class,
        NewsAPClientModule.class,
        OkHttpClientModule.class,
        RoomModule.class,
        NewsRepositoryModule.class,
        DBboundaryCallbackModule.class
})


public interface AppComponent {
    void inject(DBDataSource dbDataSource);
    void inject(NetDataSource netDataSource);
    void inject(NewsRepositoryImpl newsRepository);
    void inject(MainActivityPresenter mainActivityPresenter);
    void inject(DBboundaryCallback dBboundaryCallback);

}
