package com.example.vj20222.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vj20222.dao.UserDao;
import com.example.vj20222.entities.Anime;
import com.example.vj20222.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
//    public abstract AnimeDao userDao();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "vj20222_db")
                .allowMainThreadQueries()
                .build();
    }
}
