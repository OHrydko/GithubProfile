package com.example.mvpexample;

import android.app.Application;

import com.example.mvpexample.di.Component;
import com.example.mvpexample.di.DaggerRetrofitComponent;
import com.example.mvpexample.di.RetrofitModule;
import com.example.mvpexample.di.RoomModule;

public class App extends Application {

    private static Component component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerRetrofitComponent.builder()
                .application(this)
                .retrofitModule(new RetrofitModule())
                .roomModule(new RoomModule())
                .build();
    }

    public static Component getComponent() {
        return component;
    }
}
