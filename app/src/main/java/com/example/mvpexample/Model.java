package com.example.mvpexample;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;


import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;


public class Model implements MainInterface.Model {

    @Override
    public void load(CallBackGithub callBackGithub, String userName) {
        NetworkUtils.getService().getProfile(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Github>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Github github) {
                        callBackGithub.result(github);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBackGithub.error("" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void loadRepository(CallBackRepository callBackRepository, String userName) {
        NetworkUtils.getService().getRepos(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ArrayList<GithubRepository>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<GithubRepository> githubRepositorises) {
                        callBackRepository.result(githubRepositorises);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBackRepository.error("" + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
