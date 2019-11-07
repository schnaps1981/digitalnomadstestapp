package com.testapp.data.database.model;

import android.telecom.Call;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

@Entity(tableName = "news",
        indices = {@Index(value = {
                "author",
                "title",
                "description",
                "url",
                "urlToImage",
                "publishedAt",
                "content"
        }, unique = true)})

public class NewsAPIItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "timestamp")
    private Long tmestamp = System.currentTimeMillis();

    @SerializedName("author")
    @ColumnInfo(name = "author")
    private String author;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    private String urlToImage;

    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    private String content;

    public Integer getId() { return id;}
    public void setId(Integer id) {this.id = id; }

    public Long getTmestamp() { return tmestamp; }
    public void setTmestamp(Long tmestamp) { this.tmestamp = tmestamp; }

    public String getAuthor() { return author;  }
    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description;}

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUrlToImage() { return urlToImage; }
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) {  this.publishedAt = publishedAt; }

    public String getContent() { return content; }
    public void setContent(String content) {  this.content = content;   }

    @Override
    public String toString()
    {
        return "Author: = " + author + "; Title = " + title + "; Description = " + description + "; Url = " + url + "; etc....";
    }

    public static DiffUtil.ItemCallback<NewsAPIItem> DiffUtilsCallback = new DiffUtil.ItemCallback<NewsAPIItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsAPIItem oldItem, @NonNull NewsAPIItem newItem) {
            return oldItem.publishedAt.equals(newItem.publishedAt);

        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsAPIItem oldItem, @NonNull NewsAPIItem newItem) {
            return
                oldItem.publishedAt.equals(newItem.publishedAt);
        }
    };
}
