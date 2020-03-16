package com.example.mvpexample.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.mvpexample.db.AppDataBase;
import com.example.mvpexample.db.Dao;

import dagger.Module;
import dagger.Provides;

@Module
public
class RoomModule {

    @Provides
    Dao getDao(AppDataBase appDataBase) {
        return appDataBase.getDao();
    }

    @Provides
    AppDataBase getDataBase(Application context) {
        return Room.databaseBuilder(context, AppDataBase.class, "db")
                .allowMainThreadQueries()
                .build();
    }
}
