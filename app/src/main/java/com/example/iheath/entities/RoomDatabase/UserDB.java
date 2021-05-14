package com.example.iheath.entities.RoomDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, CanNang.class,BMI.class, Calo.class, CaloCanNap.class, RSS.class,MonAn.class,ThanhPhanChiTiet.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    public abstract UserDAO getUserDAO();
}