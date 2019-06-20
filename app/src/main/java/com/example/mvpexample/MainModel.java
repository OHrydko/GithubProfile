package com.example.mvpexample;

import com.example.mvpexample.model.Github;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel implements MainInterface.Model {

    public static final String BASE_URL = "https://api.github.com/";
    Github mGithub;
    ApiService apiService;
    MainInterface.Presenter presenter;


    @Override
    public void load(CallBackGithub callBackGithub) {
        NetworkUtils.getService().getProfile("OHrydko")
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


}
