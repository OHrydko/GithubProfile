package com.example.mvpexample.network;


import android.annotation.SuppressLint;

import com.example.mvpexample.App;
import com.example.mvpexample.callback.CallBackGithub;
import com.example.mvpexample.callback.CallBackRepository;
import com.example.mvpexample.main.MainInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Repository {


    @SuppressLint("CheckResult")
    public void load(CallBackGithub callBackGithub, String userName) {
        App.getComponent().getApi().getProfile(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(callBackGithub::result,
                        throwable -> callBackGithub.error(" " + throwable));

    }

    @SuppressLint("CheckResult")
    public void loadRepository(CallBackRepository callBackRepository, String userName) {
        App.getComponent().getApi().getRepos(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(callBackRepository::result,
                        throwable -> callBackRepository.error("" + throwable));
    }


}
