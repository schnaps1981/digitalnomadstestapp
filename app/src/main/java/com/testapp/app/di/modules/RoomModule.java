package com.testapp.app.di.modules;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.testapp.app.Constants;
import com.testapp.data.database.DataBase;
import com.testapp.data.database.NewsDBDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    private DataBase dataBase;

    public RoomModule(Context context) {
        dataBase = Room
                .databaseBuilder(context, DataBase.class, Constants.DB_NAME)
                .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
                .build();
    }

    @Singleton
    @Provides
    DataBase providesRoomDatabase() {
        return dataBase;
    }

    @Singleton
    @Provides
    NewsDBDao providesNewsDBDao() {
        return dataBase.DatabaseNewsDao();
    }
}
