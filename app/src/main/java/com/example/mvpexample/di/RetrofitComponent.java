package com.example.mvpexample.di;

import com.example.mvpexample.ApiService;

import dagger.Component;


@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    ApiService getApi();
}
