package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "RSS" ,primaryKeys = {"id"})
public class RSS implements Serializable {

    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @NonNull
    @ColumnInfo(name="url")
    private String url;

    @NonNull
    @ColumnInfo(name="thumnail")
    private String thumnail;

    @NonNull
    @ColumnInfo(name="date")
    private String date;

    @Override
    public String toString() {
        return "RSS{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumnail='" + thumnail + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(@NonNull String thumnail) {
        this.thumnail = thumnail;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public RSS(int id, @NonNull String title, @NonNull String url, @NonNull String thumnail, @NonNull String date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumnail = thumnail;
        this.date = date;
    }
}
