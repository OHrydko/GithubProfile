package com.example.mvpexample.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM user")
    List<Github> getAllFromUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(Github user);

    @Query("SELECT COUNT(*) FROM user where lower(login) = :login")
    int checkLoginExistsUser(String login);

    @Query("SELECT * FROM user WHERE lower(login) = :login")
    Github getUser(String login);

    @Query("SELECT * FROM repository")
    GithubRepository getAllFromRepos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepos(List<GithubRepository> repository);

    @Query("SELECT COUNT(*) FROM repository where lower(login) = :login")
    int checkLoginExistsRepos(String login);

    @Query("SELECT * FROM repository WHERE lower(login) = :login")
    List<GithubRepository> getRepos(String login);
}
