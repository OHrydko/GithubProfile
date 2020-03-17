package com.example.mvpexample.di;

import android.app.Application;

import com.example.mvpexample.db.Dao;
import com.example.mvpexample.network.ApiService;

import dagger.BindsInstance;


@dagger.Component(modules = {RetrofitModule.class, RoomModule.class})
public interface Component {
    ApiService getApi();

    Dao getDao();

    @dagger.Component.Builder
    interface Builder {
        Component build();

        Builder retrofitModule(RetrofitModule retrofitModule);

        Builder roomModule(RoomModule roomModule);

        @BindsInstance
        Builder application(Application application);

    }
}
