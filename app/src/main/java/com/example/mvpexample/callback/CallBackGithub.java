package com.example.mvpexample.callback;

import com.example.mvpexample.model.Github;

public interface CallBackGithub {
    void result(Github github);

    void error(String message);
}
