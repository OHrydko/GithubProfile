package com.example.mvpexample;


import android.annotation.SuppressLint;

import com.example.mvpexample.callback.CallBackGithub;
import com.example.mvpexample.callback.CallBackRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Model implements MainInterface.Model {


    @SuppressLint("CheckResult")
    @Override
    public void load(CallBackGithub callBackGithub, String userName) {
        App.getComponent().getApi().getProfile(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(github -> callBackGithub.result(github),
                        throwable -> callBackGithub.error(" " + throwable));

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadRepository(CallBackRepository callBackRepository, String userName) {
        App.getComponent().getApi().getRepos(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(githubRepositories -> callBackRepository.result(githubRepositories),
                        throwable -> callBackRepository.error("" + throwable));
    }


}
