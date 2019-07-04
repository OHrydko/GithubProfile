package com.example.mvpexample;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

public class Presenter implements MainInterface.Presenter {

    MainInterface.Model model;
    MainInterface.View mView;


    public Presenter(MainInterface.View mView) {
        this.mView = mView;
        this.model = new Model();
    }

    @Override
    public void onButtonWasClicked(String userName) {
        model.load(new CallBackGithub() {
            @Override
            public void result(Github github) {
                mView.showUser(github);
            }

            @Override
            public void error(String message) {
                mView.showError(message);
            }
        }, userName);

    }

    @Override
    public void sendUserRepository(String userName) {
        model.loadRepository(new CallBackRepository() {
            @Override
            public void result(ArrayList<GithubRepository> githubRepository) {
                mView.showRepository(githubRepository);
            }

            @Override
            public void error(String message) {
                mView.showError(message);
            }
        }, userName);
    }


    @Override
    public void onDestroy() {

    }
}
