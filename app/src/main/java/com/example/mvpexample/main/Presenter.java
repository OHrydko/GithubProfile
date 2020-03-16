package com.example.mvpexample.main;

import com.example.mvpexample.App;
import com.example.mvpexample.callback.CallBackGithub;
import com.example.mvpexample.db.Dao;
import com.example.mvpexample.model.Github;
import com.example.mvpexample.network.Repository;

public class Presenter implements MainInterface.Presenter {

    private Repository model;
    private MainInterface.View mView;
    private Dao dao;

    Presenter(MainInterface.View mView) {
        this.mView = mView;
        this.model = new Repository();
        dao = App.getComponent().getDao();
    }

    @Override
    public void onButtonWasClicked(String userName) {
        mView.showLoading();
        if (dao.checkLoginExistsUser(userName) == 0) {
            model.load(new CallBackGithub() {
                @Override
                public void result(Github github) {
                    mView.hideLoading();
                    mView.showUser(github);
                    new Thread(() -> dao.insertUser(github)).run();
                }

                @Override
                public void error(String message) {
                    mView.hideLoading();
                    mView.showError(message);
                }
            }, userName);
        } else {
            mView.showUser(dao.getUser(userName.toLowerCase()));
            mView.hideLoading();

        }

    }


}
