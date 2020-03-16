package com.example.mvpexample.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

@Database(entities = {Github.class, GithubRepository.class}, version = 1, exportSchema = false)
abstract public class AppDataBase extends RoomDatabase {
    public abstract Dao getDao();
}
