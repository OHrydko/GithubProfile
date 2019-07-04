package com.example.mvpexample;


import android.annotation.SuppressLint;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Model implements MainInterface.Model {


    @Override
    public void load(CallBackGithub callBackGithub, String userName) {
        App.getComponent().getApi().getProfile(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(github -> callBackGithub.result(github),
                        throwable -> callBackGithub.error(" " + throwable));

    }

    @Override
    public void loadRepository(CallBackRepository callBackRepository, String userName) {
        App.getComponent().getApi().getRepos(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(githubRepositories -> callBackRepository.result(githubRepositories),
                        throwable -> callBackRepository.error("" + throwable));
    }


}
