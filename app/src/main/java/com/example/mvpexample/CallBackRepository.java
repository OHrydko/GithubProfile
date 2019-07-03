package com.example.mvpexample;

import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;


public interface CallBackRepository {
    void result(ArrayList<GithubRepository> githubRepository);
    void error(String message);
}
