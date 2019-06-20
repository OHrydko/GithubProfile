package com.example.mvpexample;

import com.example.mvpexample.model.Github;

public class MainPresenter implements MainInterface.Presenter {

    MainInterface.Model model;
    MainInterface.View mView;

    String message;



    public MainPresenter(MainInterface.View mView) {
        this.mView = mView;
        this.model = new MainModel();
    }

    @Override
    public void onButtonWasClicked() {
        model.load(new CallBackGithub() {
            @Override
            public void result(Github github) {
                mView.showText(github.getLogin());
            }

            @Override
            public void error(String message) {

            }
        });
        mView.showText(message);
    }


    @Override
    public void onDestroy() {

    }
}
