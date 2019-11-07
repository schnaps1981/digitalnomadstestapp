package com.testapp.app;

import android.app.Application;

import com.testapp.app.di.component.AppComponent;
import com.testapp.app.di.component.DaggerAppComponent;
import com.testapp.app.di.modules.AppContextModule;
import com.testapp.app.di.modules.RoomModule;

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent
                .builder()
                .appContextModule(new AppContextModule(getApplicationContext()))
                .roomModule(new RoomModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
