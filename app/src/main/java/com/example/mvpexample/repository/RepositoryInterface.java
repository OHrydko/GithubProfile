package com.example.mvpexample.repository;

import com.example.mvpexample.model.GithubRepository;

import java.util.List;

public interface RepositoryInterface {

    interface View {

        void showError(String errors);

        void showLoading();

        void hideLoading();

        void showRepository(List<GithubRepository> githubRepository);
    }

    interface Presenter {
        void onButtonWasClicked(String userName);

    }
}
