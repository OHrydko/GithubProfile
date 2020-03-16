package com.example.mvpexample.callback;

import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;
import java.util.List;


public interface CallBackRepository {
    void result(List<GithubRepository> githubRepository);

    void error(String message);
}
