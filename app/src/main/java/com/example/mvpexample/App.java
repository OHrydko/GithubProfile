package com.example.mvpexample;

import android.app.Application;

import com.example.mvpexample.di.DaggerRetrofitComponent;
import com.example.mvpexample.di.RetrofitComponent;
import com.example.mvpexample.di.RetrofitModule;

public class App extends Application {

    private static RetrofitComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public static RetrofitComponent getComponent() {
        return component;
    }
}
