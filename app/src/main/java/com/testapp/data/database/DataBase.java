package com.testapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.testapp.data.database.model.NewsAPIItem;

@Database(entities = NewsAPIItem.class, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract NewsDBDao DatabaseNewsDao();
}
