package com.testapp.data.database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.testapp.data.database.model.NewsAPIItem;

import java.util.List;

@Dao
public interface NewsDBDao {
    @Transaction
    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    DataSource.Factory<Integer, NewsAPIItem> getNewsByPublish();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (List<NewsAPIItem> newsAPIItems);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (NewsAPIItem newsAPIItem);


    @Transaction
    @Query("DELETE FROM news WHERE timestamp < :cacheExpiredTime")
    void deleteOldNews(Long cacheExpiredTime);

}
