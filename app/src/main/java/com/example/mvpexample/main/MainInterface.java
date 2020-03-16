package com.example.mvpexample.main;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.List;

public interface MainInterface {


    interface View {
        void showUser(Github github);

        void showError(String errors);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void onButtonWasClicked(String userName);
    }
}
