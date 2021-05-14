package com.example.iheath;

import android.app.Application;

import androidx.room.Room;

import com.example.iheath.entities.RoomDatabase.UserDB;


public class App extends Application {
    private  static App instance;
    private UserDB userDB;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initDB();
    }

    private void initDB() {
        userDB= Room.databaseBuilder(this,UserDB.class, "iheath.db").createFromAsset("iheath.db").build();
    }

    public  static App getInstance(){
        return  instance;
    }

    public UserDB getUserDB() {
        return userDB;
    }
}
