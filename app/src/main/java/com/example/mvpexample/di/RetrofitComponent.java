package com.example.mvpexample.di;

import android.app.Application;

import com.example.mvpexample.db.Dao;
import com.example.mvpexample.network.ApiService;

import dagger.BindsInstance;
import dagger.Component;


@Component(modules = {RetrofitModule.class, RoomModule.class})
public interface RetrofitComponent {
    ApiService getApi();

    Dao getDao();

    @Component.Builder
    interface Builder {
        RetrofitComponent build();

        Builder retrofitModule(RetrofitModule retrofitModule);

        Builder roomModule(RoomModule roomModule);

        @BindsInstance
        Builder application(Application application);


    }
}
